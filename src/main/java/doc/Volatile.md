Volatile Gurantee --->

Declaring a variable volatile thus guarantees the visibility for other threads of writes to that variable.

In the scenario given above, where one thread (T1) modifies the counter, and another thread (T2) reads the counter (but
never modifies it), declaring the counter variable volatile is enough to guarantee visibility for T2 of writes to the
counter variable.

**Full volatile Visibility Guarantee**</br>
Actually, the visibility guarantee of Java volatile goes beyond the volatile variable itself. The visibility guarantee
is as follows:

1. If Thread A writes to a volatile variable and Thread B subsequently reads the same volatile variable, then all
   variables visible to Thread A before writing the volatile variable, will also be visible to Thread B after it has
   read the volatile variable.
2. If Thread A reads a volatile variable, then all all variables visible to Thread A when reading the volatile variable
   will also be re-read from main memory.

**The Java volatile Happens-Before Guarantee**</br>
To address the instruction reordering challenge, the Java volatile keyword gives a "happens-before" guarantee, in
addition to the visibility guarantee. The happens-before guarantee guarantees that:

Reads from and writes to other variables cannot be reordered to occur after a write to a volatile variable, if the reads
/ writes originally occurred before the write to the volatile variable.</br>

1. The reads / writes before a write to a volatile variable are guaranteed to "happen before" the write to the volatile
   variable. Notice that it is still possible for e.g. reads / writes of other variables located after a write to a
   volatile to be reordered to occur before that write to the volatile. Just not the other way around. From after to
   before is allowed, but from before to after is not allowed.
2. Reads from and writes to other variables cannot be reordered to occur before a read of a volatile variable, if the
   reads / writes originally occurred after the read of the volatile variable. Notice that it is possible for reads of
   other variables that occur before the read of a volatile variable can be reordered to occur after the read of the
   volatile. Just not the other way around. From before to after is allowed, but from after to before is not allowed