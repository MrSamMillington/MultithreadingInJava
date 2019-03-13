#Notes

### Info

A collection of notes and code snippets for advanced java multithreading. 
All credit for code samples goes to [caveofprogramming.com](https://caveofprogramming.com/).

## Threading

`Runnable` is basically a task, whereas a `Thread` runs the runnable task

``` 
Thread t = new Thread(new Runnable...)
```

Similarly an `ExecutorService` collects a series of `Runnable`s, and re-uses the thread objects where possible.

A thread's `.join()` method will wait for the thread to finish

## Volatile variables

Declaring a variable volatile stops any threads from caching the variable. 
This can cause issues when variables are used to determine if `while` loops should be ended.

## Synchronized

`Synchronized` methods on a thread will use the class itself as a lock. Good practice is to 
define locks to use within the method in a `synchronized` code block.


## Latches

`CountDownLatch` makes threads wait until the latch is counted down to 0. ThreadSafe.


## Producer Consumer Pattern

Can use blocking queues which are thread safe. These can be used to create (produce) jobs to process (consume) using threads.

## Wait and Notify

#### wait

`.wait(int timeout)` Every object has a wait method. Waits in a way that doesnt consume lots of system 
resources. Can only be called within `sychronized` code block. Thread will lose control of the lock.
Thread must regain control of lock for `synchronized` code block to resume running. 

#### notify

`.notify()` can also only be called within a `synchronized` code block. This notifies other threads 
that the lock is to be released. **Notify DOES NOT relinquish control of the lock - so it should be 
the last line in your `synchronized` code block.**

## Reentrant Locks

Alternative to using the synchronized keyword. Once a thread has acquired the lock, it may lock it again.
A locked thread will have to unlock it the same number of times for it be truly unlocked. 

Unlock should always be called in a `finally` block to ensure the lock is released, under any circumstance.

```
private Lock lock = new ReentrantLock(); //

lock.lock();
try {
    increment();
}catch (Exception e){
   e.printStackTrace();
}finally{
    lock.unlock();
}
```

Notify on a `ReentrantLock` is called signal, and are called from the `Condition` class. `Condition` is created 
with `lock.newCondition()`.

## Deadlock

Deadlock is when two threads attempt to get access to locks that the other has. 

To counter, when 2 locks are needed, write a `requireLocks(Lock one, Lock two)` method to safely acquire the locks
together,

## Semaphores




