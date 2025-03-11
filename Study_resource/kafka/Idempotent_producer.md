
An idempotent producer is a producer in a distributed messaging system (such as Apache Kafka) that: </br>
***ensures that messages are not duplicated even if they are retried due to network failures or other issues.*** 


***This guarantees exactly-once delivery semantics at the producer level.***


## How It Works

**When the idempotent producer is enabled:**
1.	Each message sent by the producer is assigned a sequence number.
2.	The broker (Kafka server) keeps track of the latest sequence number for each producer.
3.	If a producer retries sending a message due to a failure, the broker can detect duplicates based on the sequence number and discard them.

**Key Features:**
* Prevents duplicate messages even if the producer retries sending.
* Ensures exactly-once delivery at the producer level.
* Enabled by default in Kafka 3.0+ (enable.idempotence=true).
* Requires a producer ID (PID), which is assigned when the producer is initialized.

```java
Properties props = new Properties();
props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true"); //TODO:  Enables idempotency

KafkaProducer<String, String> producer = new KafkaProducer<>(props);
```

