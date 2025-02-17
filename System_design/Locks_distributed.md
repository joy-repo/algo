
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




