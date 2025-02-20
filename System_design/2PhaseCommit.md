
## What is Serializability :

We use isolation, to separate concurrently running 
transactions from one another, making it impossible 
for them to be interdependent. The ability of each transaction 
to act as though it is the only one currently active throughout 
the whole database is known as serializability. 
This process is called serializable isolation.

Although the transactions may have been running concurrently, 
the database ensures that their commits produce an outcome 
similar to as if they were running serially, i.e., (one after the other)



There are three ways to achieve serializability in databases:

* Actual Serial Execution: This is the actual serial execution of transactions.
* Two-Phase Locking (2PL): We will discuss 2PL in this chapter.
* Serializable Snapshot Isolation (SSI): This is an optimistic concurrency control technique and beyond the scope of this course.

## 2P Lock :
https://www.youtube.com/watch?v=lceenm34m-w&t=812s


## FROM CHAT GPT :

1. How Two-Phase Locking Works

2PL divides a transaction’s locking process into two phases:

📌 Phase 1: Growing Phase
•	A transaction can acquire locks (read or write) on data items.
•	No locks are released in this phase.
•	The transaction keeps acquiring locks until it reaches its lock point (the moment when it acquires its last lock).

📌 Phase 2: Shrinking Phase
•	The transaction releases locks as it no longer needs them.
•	No new locks can be acquired after releasing any lock.
•	The transaction must commit or abort once it releases all locks.

Example of Two-Phase Locking

Step|	Transaction T1	|        Locking Status
---|---|---
1|	Acquire Lock(X)|	            Growing Phase
2|	Acquire Lock(Y)|	            Growing Phase
3|	Perform operation on X, Y|	Growing Phase
4|	Release Lock(X)	 |           Shrinking Phase
5|	Release Lock(Y)	|            Shrinking Phase
6|	Commit	   |                 Done


Once the shrinking phase starts, no new locks can be acquired.

2. Types of Two-Phase Locking

📌 Strict Two-Phase Locking (Strict 2PL)
•	All exclusive (write) locks are held until the transaction commits or aborts.
•	Prevents cascading aborts by ensuring a rollback doesn’t affect other transactions.
•	Used in most databases (e.g., MySQL, PostgreSQL, SQL Server).

✅ Advantage: Ensures strict serializability.
❌ Disadvantage: Can cause deadlocks and reduced concurrency.

📌 Rigorous Two-Phase Locking
•	All locks (read and write) are held until the transaction commits or aborts.
•	Stronger than Strict 2PL, offering even better consistency.

✅ Advantage: Ensures highest level of isolation.
❌ Disadvantage: Can lead to long transaction wait times.



**SERIALIZABILITY :**  <br />
We use isolation, to separate concurrently running transactions from one another, 
making it impossible for them to be interdependent. The ability of each transaction 
to act as though it is the only one currently active throughout the whole database is 
known as serializability. This process is called serializable isolation.





------------------------------------------------------------------------------------------

**Example: Two-Phase Locking (2PL) in a Distributed Database**

Let’s consider a banking system where a distributed database is used to store account balances. 
The system has two sites (nodes):

• Site A manages Account_1

• Site B manages Account_2

***A money transfer transaction (T1) moves $500 from Account_1 to Account_2 :***

**Step 1:** Transaction T1 Begins (Growing Phase)

1. T1 requests a lock on Account_1 at Site A (read + write lock).

• Lock granted ✅

2. T1 requests a lock on Account_2 at Site B (read + write lock).

• Lock granted ✅

At this point, T1 has acquired all necessary locks and reaches the locking point.

**Step 2:** Transaction Executes the Transfer

• Read Account_1 balance (e.g., $2000) → subtract $500 → update balance to $1500.

• Read Account_2 balance (e.g., $1000) → add $500 → update balance to $1500.

**Step 3:** Transaction T1 Begins the Shrinking Phase

1. T1 commits the transaction.

2. Locks are released in reverse order:

• Release lock on Account_2 at Site B.

• Release lock on Account_1 at Site A.



Now, other transactions can access Account_1 and Account_2.

SQL Representation of 2PL in a Distributed Database

```sql

-- Start Transaction at Site A
START TRANSACTION;
LOCK TABLES Account_1 WRITE;

-- Deduct $500 from Account_1
UPDATE Account SET balance = balance - 500 WHERE account_id = 1;

-- Commit at Site A (Locks still held)
COMMIT;

-- Start Transaction at Site B
START TRANSACTION;
LOCK TABLES Account_2 WRITE;

-- Add $500 to Account_2
UPDATE Account SET balance = balance + 500 WHERE account_id = 2;

-- Commit and release locks
COMMIT;
UNLOCK TABLES;
```

**What If There’s a Deadlock?**

Imagine another transaction T2 trying to do the opposite (transfer from Account_2 to Account_1).

• T1 locks Account_1 at Site A and waits for Account_2 at Site B.

• T2 locks Account_2 at Site B and waits for Account_1 at Site A.

• Both transactions are stuck waiting → Deadlock!

**Deadlock Prevention Techniques:**

**1. Timeouts:** </br>
If a transaction waits too long, it is rolled back.

**2. Wound-Wait Strategy:**

• If an older transaction (T1) requests a lock held by a newer transaction (T2), 
T2 is aborted (“wounded”) and restarted later.

**3. Wait-Die Strategy:**

• If a younger transaction (T2) requests a lock held by an older transaction (T1), 
T2 “dies” (waits/restarts) instead of blocking T1.


**Deadlock Detection & Recovery in Two-Phase Locking (2PL):**

📌 **Wait-For Graph (WFG)** </br>
•	A directed graph where: <br>
•	Each node represents a transaction.</br>
•	A directed edge T1 → T2 means T1 is waiting for a lock held by T2.<br>
•	If the graph contains a cycle, a deadlock has occurred.</br>

🔹 Example of a Wait-For Graph Deadlock:

```
T1 → T2
T2 → T1  (Cycle detected!)
```
T1 waits for T2, but T2 is also waiting for T1 → Deadlock!
The system must now select a transaction to abort and break the cycle.

**How Databases Use Wait-For Graphs:** </br>

•	*SQL Server, PostgreSQL, and Oracle* periodically check for cycles in the wait-for graph. </br>
•	If a cycle is found, one transaction is forcibly rolled back.

**Timeout-Based Deadlock Detection** </br>

•	Each transaction has a wait timeout (e.g., 5-10 seconds). </br>
•	If a transaction waits too long for a lock, it is assumed to be in a deadlock and aborted. </br>
•	Simple and efficient, but might abort transactions unnecessarily if delays are 
caused by slow execution rather than deadlock.</br>

✅ Used in: *MySQL (InnoDB)* and some *NoSQL databases*. </br>
❌ Drawback: False positives (aborting transactions that were not actually in a deadlock).


**Deadlock Recovery :**

Once a deadlock is detected, **the system must recover by breaking the cycle**. </br>
There are two common strategies:

One transaction (the victim) is rolled back, allowing others to proceed.</br>
The victim is chosen based on: </br>
•	**Priority:** Lower-priority transactions are rolled back. </br>
•	**Transaction Age:** Younger transactions are aborted first. </br>
•	**Resources Used:** Transactions using more locks/resources are sacrificed. </br>
•	**Transaction Type:** Read-only transactions are preferred over write-heavy transactions for abortion. </br>

🔹 Example: </br>
•	T1 holds Lock(X), wants Lock(Y). </br>
•	T2 holds Lock(Y), wants Lock(X). </br>
•	The system detects a deadlock and aborts T2 since it’s the younger transaction. </br>



# Summary

✔ Growing Phase: Locks are acquired but not released.

✔ Shrinking Phase: Once a lock is released, no new locks can be acquired.

✔ Ensures Serializability but can cause deadlocks in a distributed setup.

✔ Solutions: Deadlock detection, timeouts, wait-die, and wound-wait.


**Types of Two-Phase Locking :**

📌 *Strict Two-Phase Locking (Strict 2PL):*
•	All exclusive (write) locks are held until the transaction commits or aborts.
•	Prevents cascading aborts by ensuring a rollback doesn’t affect other transactions.
•	Used in most databases (e.g., MySQL, PostgreSQL, SQL Server).

✅ Advantage: Ensures strict serializability.</br>
❌ Disadvantage: Can cause deadlocks and reduced concurrency.

📌 *Rigorous Two-Phase Locking:*
•	All locks (read and write) are held until the transaction commits or aborts.
•	Stronger than Strict 2PL, offering even better consistency.

✅ Advantage: Ensures highest level of isolation.</br>
❌ Disadvantage: Can lead to long transaction wait times.



```java

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadlockExample {
    private static final Lock lock1 = new ReentrantLock();
    private static final Lock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                lock1.lock();
                System.out.println("Thread 1 acquired lock1");

                Thread.sleep(100);

                lock2.lock();
                System.out.println("Thread 1 acquired lock2");

                lock2.unlock();
                lock1.unlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                lock2.lock();
                System.out.println("Thread 2 acquired lock2");

                Thread.sleep(100);

                lock1.lock();
                System.out.println("Thread 2 acquired lock1");

                lock1.unlock();
                lock2.unlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
    }
}
```

