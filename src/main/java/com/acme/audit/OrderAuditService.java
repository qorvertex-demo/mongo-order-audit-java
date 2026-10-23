package com.acme.audit;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/** Reads the shared `orders` collection (populated by mongo-order-api) and
 *  writes audit records to its own `audit_log` collection. */
public class OrderAuditService {
    private final MongoDatabase db;

    public OrderAuditService(MongoDatabase db) { this.db = db; }

    public long auditOrders() {
        MongoCollection<Document> orders = db.getCollection("orders");
        long count = orders.countDocuments();
        for (Document o : orders.find()) {
            db.getCollection("audit_log").insertOne(
                new Document("orderId", o.get("_id")).append("action", "seen"));
        }
        return count;
    }

    public void purge() {
        db.getCollection("audit_log").deleteMany(new Document("stale", true));
    }
}
