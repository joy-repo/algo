

## What is volatile in Java?

The volatile keyword in Java is used to ensure visibility of variable changes across threads. It guarantees that:
1.	All reads of a volatile variable always see the latest value written by any thread.
2.	Writes to a volatile variable are immediately visible to other threads.
3.	Prevents reordering of reads/writes due to compiler optimizations.

However, volatile does NOT provide atomicity for compound operations (like count++).


## How volatile Works Internally?

When a variable is declared as volatile:
1.	Writes to the variable are **directly flushed to main memory.**
2.	Reads always fetch the latest **value from main memory, bypassing CPU caches.**
3.	JVM adds **memory barriers (happens-before relationship)** to prevent instruction reordering.

