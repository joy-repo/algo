# How to Ensure Exactly-Once Delivery in Kafka?

**HOW DO TRANSACTION WORK :** https://www.youtube.com/watch?v=Ki2D2o9aVl8

Ensuring end-to-end deduplication in a distributed messaging system (like Apache Kafka) involves handling duplicates at multiple stages:
1.	**Producer Level** – Prevent duplicate message production.
2.	**Broker Level** – Ensure idempotent writes.
3.	**Consumer Level** – Handle duplicate processing.

### 1. Enable Idempotent Producer (Avoid Duplicate Messages at the Source)





