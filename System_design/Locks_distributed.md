
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

Step	Transaction T1	        Locking Status
1	Acquire Lock(X)	            Growing Phase
2	Acquire Lock(Y)	            Growing Phase
3	Perform operation on X, Y	Growing Phase
4	Release Lock(X)	            Shrinking Phase
5	Release Lock(Y)	            Shrinking Phase
6	Commit	                    Done


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

# 2 PC - Educative

**SERIALIZABILITY :**  <br />
We use isolation, to separate concurrently running transactions from one another, 
making it impossible for them to be interdependent. The ability of each transaction 
to act as though it is the only one currently active throughout the whole database is 
known as serializability. This process is called serializable isolation.

