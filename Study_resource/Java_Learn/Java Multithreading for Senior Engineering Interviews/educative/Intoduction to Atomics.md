

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

## Atomic Processor Instructions

Modern processors have instructions that can atomically execute compound operations offering a compromise between locking and volatile variables. Hardware support for concurrency is ubiquitous in present-day processors and the most well-known of these instructions is the Compare and Swap instruction or CAS for short. The CAS instruction is the secret sauce behind atomically executing compound operations.


## Compare and Swap
In general, the CAS instruction has three operands:

1. A memory location, say M,representing the variable we desire to manipulate.
2. An expected value for the variable, say A. This is the latest value seen for the variable.
3. The new value, say B, which we want the variable to update to.

## CAS instruction works by performing the following actions atomically:

1. Check the latest value of the memory location M.

2. If the memory location has a value other than A, then it implies that another thread changed the variable since the last time we examined it and therefore the requested update operation should be aborted.

3. If the variable’s value is indeed A, then it implies that no other thread has had a chance to change the variable to a different value than A, since we last examined the variable’s value and therefore we can proceed to update the variable/memory location to the new value B.


## ABA Problem#

CAS succeeds even if the value of a shared variable is changed from A to B and then back to A. Consider the following sequence:

1. A thread T1 reads the value of a shared variable as A and desires to change it to B. After reading the variable’s value, thread

2. T1 undergoes a context switch. Another thread, T2 comes along, changes the value of the shared variable from A to B and then back to A from B.

3. Thread T1 is scheduled again for execution and invokes CAS with A as the expected value and B as the new value. CAS succeeds since the current value of the variable is A, even though it changed to B and then back to A in the time thread T1 was context switched.

## Solution to ABA--AtomicStampedReference 

By tracking changes with a "stamp" (integer version number), the ABA problem is avoided

```java
AtomicStampedReference(V initialRef, int initialStamp)

// initialRef: Initial reference value.

//initialStamp: Initial stamp (integer) representing the version or state.
```


**Important Methods::** </br>

Method	| Description
---|---
getReference()|	Returns the current reference.
getStamp()	| Returns the current stamp.
get(int[] stampHolder) |	Retrieves the current reference and stamp, storing the stamp in the provided array.
compareAndSet(V expectedRef, V newRef, int expectedStamp, int newStamp) |	Atomically sets the reference and stamp if both match the expected values.
set(V newRef, int newStamp)	 | Unconditionally sets the reference and stamp.
attemptStamp(V expectedRef, int newStamp) |	Atomically sets the stamp if the current reference matches the expected reference.

```java

import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampedReferenceExample {

    public static void main(String[] args) {
        String initialRef = "A";
        int initialStamp = 0;

        AtomicStampedReference<String> atomicStampedRef = new AtomicStampedReference<>(initialRef, initialStamp);

        System.out.println("Initial Reference: " + atomicStampedRef.getReference());
        System.out.println("Initial Stamp: " + atomicStampedRef.getStamp());

        // Thread 1: Successful update with correct stamp
        boolean isUpdated = atomicStampedRef.compareAndSet("A", "B", 0, 1);
        System.out.println("Update success: " + isUpdated); // true
        System.out.println("Current Reference: " + atomicStampedRef.getReference()); // B
        System.out.println("Current Stamp: " + atomicStampedRef.getStamp()); // 1

        // Simulate ABA problem by setting it back to "A" without changing the stamp
        atomicStampedRef.set("A", atomicStampedRef.getStamp());

        // Thread 2: Attempting update with old stamp (which will fail)
        boolean isUpdatedAgain = atomicStampedRef.compareAndSet("A", "C", 0, 2);
        System.out.println("Update success (ABA problem avoided): " + isUpdatedAgain); // false

        // Thread 2: Attempting update with correct stamp (which will succeed)
        boolean isUpdatedWithCorrectStamp = atomicStampedRef.compareAndSet("A", "C", 1, 2);
        System.out.println("Update success with correct stamp: " + isUpdatedWithCorrectStamp); // true
        System.out.println("Final Reference: " + atomicStampedRef.getReference()); // C
        System.out.println("Final Stamp: " + atomicStampedRef.getStamp()); // 2
    }
}

```