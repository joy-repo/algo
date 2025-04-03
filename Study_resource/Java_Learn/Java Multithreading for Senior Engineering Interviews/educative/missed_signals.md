

## Missed Signal Problem
When a signal (notify()) is sent before the waiting thread (wait()) starts waiting, it will miss the signal. This can happen when:

The notifying thread runs before the waiting thread starts waiting.

The waiting thread hasn't yet called wait() when the notifying thread calls notify().


```java
public class MissedSignalExample {
    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread waiter = new Thread(() -> {
            synchronized (lock) {
                try {
                    System.out.println("Waiter: Waiting for the signal...");
                    // Missed signal happens if notify() is called before wait()
                    lock.wait();
                    System.out.println("Waiter: Received the signal!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread notifier = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Notifier: Sending signal...");
                lock.notify();  // If this runs before wait() is called, the signal is missed.
                System.out.println("Notifier: Signal sent.");
            }
        });

        notifier.start();  // Notifier runs before waiter, so signal is missed
        Thread.sleep(100);  // Adding a delay to ensure the issue occurs
        waiter.start();
    }
}

```


***Output :***

```

Notifier: Sending signal...
Notifier: Signal sent.
Waiter: Waiting for the signal...

```

How to fix it :

[FixedWaitNotifyExample.java](..%2F..%2F..%2F..%2Fdsa%2Fsrc%2Fmain%2Fjava%2Fcom%2Fjava%2Fconcurrency_probs%2FFixedWaitNotifyExample.java)