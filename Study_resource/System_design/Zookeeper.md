# Apache ZooKeeper - Basics

Apache ZooKeeper is a distributed coordination service used in distributed systems to manage configuration, leader election, and synchronization. It ensures fault tolerance and consistency across multiple nodes.

🔹 Why is ZooKeeper Needed?

In distributed systems, nodes need coordination for tasks like leader election, distributed locks, and service discovery. Without coordination, race conditions, deadlocks, and inconsistencies can occur.

📌 Example Use Cases: </br>
✔ Kafka: Manages broker coordination and leader election. </br>
✔ HBase: Stores metadata and ensures high availability. </br>
✔ Hadoop: Manages cluster configuration and synchronization. </br>

🔹 Key Concepts in ZooKeeper

1️⃣ ZNodes (ZooKeeper Nodes) </br>
•	ZooKeeper stores data in a tree-like structure called ZNodes. </br>
•	Each ZNode acts like a file but supports real-time event notifications. </br>

📌 Example ZooKeeper Tree:
```
/kafka
├── /brokers
│    ├── /ids/1  (Broker 1)
│    ├── /ids/2  (Broker 2)
├── /config
│    ├── /topics
│         ├── /topic1 (Metadata for a topic)
```
✅ Types of ZNodes:

ZNode Type |	Description
---|---
Persistent |	Exists until explicitly deleted.
Ephemeral |	Auto-deleted when the client disconnects (used in leader election).
Sequential |	Appends an incrementing number (used for ordering).

2️⃣ Leader Election using ZooKeeper

Leader election ensures that only one node acts as a leader at a time.

📌 How it Works:
1.	Nodes create ephemeral-sequential ZNodes under /election.
2.	The node with the smallest sequence number is the leader.
3.	If the leader fails, the next smallest node becomes the leader.

✅ Example:
```
/election
├── node_0001 (Leader)
├── node_0002
├── node_0003
```
If node_0001 crashes, ZooKeeper elects node_0002 as the new leader.

3️⃣ ZooKeeper Watches
•	Watches notify clients when ZNode data changes.
•	Clients register a watch on a ZNode, and ZooKeeper alerts them when it updates.

✅ Example:
•	A Kafka broker watches /leader ZNode.
•	If the leader changes, ZooKeeper notifies the broker.

📌 Java Example (Watch on a ZNode):
```java


ZooKeeper zk = new ZooKeeper("localhost:2181", 2000, null);
byte[] data = zk.getData("/myZNode", new Watcher() {
    public void process(WatchedEvent event) {
        System.out.println("ZNode changed!");
    }
}, null);
```
🔹 ZooKeeper Architecture

ZooKeeper follows a Leader-Follower (Quorum-based) architecture.

Component	Description
Leader	Handles all writes and ensures consistency.
Followers	Serve read requests and replicate data.
Clients	Connect to ZooKeeper for coordination.

📌 ZooKeeper Read/Write Flow:
1️⃣ Writes (Leader Only):
•	Clients send write requests to the Leader.
•	The leader broadcasts updates to Followers.
•	A write is committed only when a majority (quorum) confirms.

2️⃣ Reads (Any Node):
•	Clients can read data from any node.
•	Reads are eventually consistent (might be slightly stale).

✅ ZooKeeper Cluster:

Client  ───► Follower 1  
───► Leader (Writes)  
───► Follower 2

🔹 ZooKeeper Use Cases

Use Case	How ZooKeeper Helps
Leader Election	Ensures only one node is the leader.
Service Discovery	Stores metadata about running services.
Configuration Management	Centralized config storage.
Distributed Locks	Prevents race conditions in concurrent processes.

✅ Real-World Applications:
•	Kafka: Manages brokers, partitions, and leader election.
•	HBase: Manages distributed database metadata.
•	Hadoop: Stores cluster configurations.

🔹 ZooKeeper Advantages

✔ Highly Available: Replication ensures fault tolerance.
✔ Strong Consistency: Ensures atomic updates to data.
✔ Scalable: Handles many read requests efficiently.
✔ Fast Notifications: Clients get updates in real time.

❌ Challenges
•	Single Leader Bottleneck: All writes go through one leader.
•	Eventual Consistency: Reads may be slightly outdated.
•	Requires Quorum: Needs a majority to be operational.

🔹 Summary

ZooKeeper is a powerful coordination tool for distributed systems. It manages leader election, service discovery, and distributed locks efficiently. Many large-scale applications like Kafka, Hadoop, and HBase rely on ZooKeeper for stability.

Would you like a step-by-step setup guide or code for leader election? 🚀