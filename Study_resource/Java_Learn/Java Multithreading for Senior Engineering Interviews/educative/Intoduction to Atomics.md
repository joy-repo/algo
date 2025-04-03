

## Locking comes with its downsides some of which include:

1. ***Thread scheduling vs useful work***
* JVM is very efficient when it comes to acquiring and releasing a lock that is
requested by a single thread. 
* However, when multiple threads attempt to acquire the same lock, only one wins and the rest must be suspended. 
* The suspension and resumption of threads is costly and introduces significant overhead and this can be an issue for scenarios where several threads contend for the same lock but execute very little functionality. 
* In such cases, the time spent in scheduling overhead overwhelms the useful work done. 
* This is true of synchronized collections where the majority of methods perform very few operations.

2. ***Priority inversion***
* A higher priority thread can be blocked by a lower priority thread that holds the lock and itself is blocked 
because of a page fault, scheduling delay etc. 
* This situation effectively downgrades the priority of the higher-priority thread to that of the lower-priority thread
since the former can’t make progress until the latter releases the lock. 
* In general, all threads that require a particular lock can’t make progress until the thread holding the lock releases it.

3. ***Liveness issues***
Locking also introduces the possibility of liveness issues such as deadlocks, livelock or simply programming bugs that 
have threads caught in infinite loops blocking other threads from making progress.

4. ***Locking, a heavyweight mechanism***
* In general locking is a heavyweight mechanism, especially for fine-grained tasks such as manipulating a counter. 
* Locking is akin to assuming the worst or preparing for the worst possible scenario, 
i.e. the thread assumes it would necessarily run into contention with another thread and acquires a lock to manipulate shared state. 
* Another approach could be to update shared state hoping it would complete without contention/interference from other participants. 
* In case contention is detected, the update operation can be failed, and if desired, reattempted later. 
* We’ll see how this approach is supported by hardware later.

