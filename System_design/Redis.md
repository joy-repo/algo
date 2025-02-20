# Redis

Redis (Remote Dictionary Server) is an open-source, in-memory data structure store that can be used as:
* A cache 
* A message broker 
* A real-time database 
* A session store

It is known for high performance, low latency, and simplicity.

## Redis Core Features

* In-Memory Storage
  * All data is stored in RAM, making read/write operations extremely fast.
  * Uses persistence (AOF, RDB) to recover data after a restart.
    * **RDB(Redis Database File) :** Periodically takes a snapshot of the dataset and writes it to disk.
    * **AOF (Append-Only File)** : Logs every write operation (real-time persistence).
    </br></br>
* Provides Write Ahead logs for all types mutations.
* Transactions (Only on 1 node in case of sharded cluster)
* Pub/sub -> push based
* TTL on keys
* LRU eviction

**Data Structures**

Data | Structure |	Description	Example |
--|--|--|
Strings	|Simple key-value pairs	| SET user:1 "Alice"
Lists|	Ordered lists (like arrays)|	RPUSH queue 1 2 3
Sets |	Unordered unique elements |	SADD countries "USA" "India"
Hashes |	Key-value pairs inside a key|	HSET user:1 name "Alice"
Sorted Sets| (ZSets)	Ordered set with scores|	ZADD leaderboard 100 "Alice"
Bitmaps|	Bit-level operations |	SETBIT users 5 1
HyperLogLogs|	Approximate cardinality counting|	PFADD visitors "user1"
Streams	| Log-like append-only structure |	XADD events * user Alice action login

## Redis Performance 

* Can handle millions of operations per second.
* Latency as low as **sub-millisecond**.
* Single-threaded but achieves high throughput via non-blocking I/O.



## Redis Internals

Redis is an in-memory data structure store used as a database, cache, and message broker.
It is extremely fast because it stores data in RAM instead of disk, 
with O(1) or O(log N) time complexity for most operations.

###  Redis Internal Components

Redis follows a single-threaded event-driven architecture and is optimized for high-speed data access. 
Let’s break down how Redis works internally.


####  Redis Memory Storage

* Redis stores all data in RAM for low-latency reads and writes.
* Data is stored in a hash table (dict) with an efficient hashing function for fast lookups.
* Each key is a Redis object, and values can be strings, lists, sets, hashes, and more.

```

+----------------+
| Key   | Value  |
+----------------+
| user1 | Alice  |
| user2 | Bob    |
| age   | 25     |
+----------------+
```

###  Redis Internal Structure

* Every operation in a node in Redis is Atomic in nature.
  * Achieved by executing only one command at a time.(no context Switch)
  * One command at a time by the **event-loop**. Does not start executing another command in between.
  * So handling concurrency,parallel processing, thread management does not come into picture.


####  Redis Event Loop Overview
(reference: https://redis.io/docs/latest/operate/oss_and_stack/reference/internals/internals-rediseventlib/)
```
+---------------------------+
|      Redis Server         |
+---------------------------+
           |
           v
+---------------------------+
|   initServer()            |   ---> Initializes Redis Server
|   - Calls aeCreateEventLoop() |
|   - Calls anetTcpServer() (creates listening socket) |
|   - Calls aeCreateTimeEvent() (sets timer) |
|   - Calls aeCreateFileEvent() (registers network event) |
+---------------------------+
           |
           v
+---------------------------+
|   aeMain()                |  ---> Enters infinite event loop
+---------------------------+
           |
           v
+---------------------------+
|   aeProcessEvents()       |  ---> Handles pending events
+---------------------------+
           |
           |-------> [ Time Events ] (e.g., serverCron tasks)
           |
           |-------> [ File Events ] (e.g., Client Requests)
           |                 |
           |                 v
           |        +-----------------------+
           |        |   aeApiPoll()         |  ---> Calls epoll_wait()
           |        |   - Checks active fds |
           |        |   - Populates fired[] |
           |        +-----------------------+
           |                 |
           |                 v
           |        +----------------------+
           |        |  acceptHandler()     | ---> Accepts new client
           |        +----------------------+
           |                 |
           |                 v
           |        +----------------------+
           |        |  createClient()      | ---> Registers client
           |        |  aeCreateFileEvent() | ---> Listens for client input
           |        +----------------------+
           |
           v
+---------------------------+
|   processTimeEvents()      |  ---> Executes time-based tasks
+---------------------------+
```

**Explanation of Flow:**
1.	initServer() sets up the event loop, networking, and timers.
2.	aeMain() starts an infinite loop.
3.	aeProcessEvents() continuously checks for:
   * **File Events** (Client connections, read/write requests).
   * **Time Events** (Background tasks like key expiration, AOF/RDB persistence).
4.	aeApiPoll() calls epoll_wait(), checking for ready descriptors.
5.	If a client connects:
*	acceptHandler() accepts the request.
*	createClient() registers the client with aeCreateFileEvent(), listening for further input.
6.	processTimeEvents() runs timed tasks and re-registers them for future execution.

