> **Part of the [QorVertex live demo](https://qorvertex.com).** A small sample application that shows one MongoDB collection (`orders`) shared by five services in four languages. It is intentionally minimal and not production code.

# mongo-order-audit-java
Java (MongoDB sync driver) audit service. Reads the shared `orders` collection
and writes to `audit_log`. Fixture for DB9 cross-language MongoDB coupling.
