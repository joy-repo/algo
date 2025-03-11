# How to Ensure Exactly-Once Delivery in Kafka?

## 1. Enable Idempotent Producer

* Ensures each message is written exactly once even if retries occur.
* Requires acks=all to be set.


```properties
enable.idempotence=true
acks=all
retries=3
max.in.flight.requests.per.connection=5  # Use 1 if using transactions
```

## 2. Use Kafka Transactions for End-to-End Exactly-Once

If you’re consuming from one Kafka topic and producing to another (e.g., in stream processing), enable Kafka Transactions.

Allows atomic writes across multiple topics/partitions.

```properties
transactional.id=unique-producer-id
```





