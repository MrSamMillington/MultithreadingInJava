package s.j.millington.sync.reentrant;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {

    private int count = 0;
    private Lock lock = new ReentrantLock(); //
    private Condition cond = lock.newCondition();

    private void increment() throws Exception{
        for(int i = 0; i < 10000; i++){
            count++;

            if(i == 7499){
                throw new Exception("Oops!");
            }
        }
    }

    public void firstThread() throws InterruptedException{

        lock.lock();

        System.out.println("waiting on first thread");
        cond.await();
        System.out.println("Woken up");

        try {
            increment();
        }catch (Exception e){
            System.out.println("increment threw an error: "+ e.getMessage());
        }finally{
            lock.unlock();
        }

    }

    public void secondThread() throws InterruptedException{

        Thread.sleep(1000);
        lock.lock();

        System.out.println("Awaiting return key");
        new Scanner(System.in).nextLine();

        System.out.println("Got return key");
        cond.signal();


        try {
            increment();
        }catch (Exception e){
            System.out.println("increment threw an error: "+ e.getMessage());
        }finally{
            lock.unlock();
        }
    }

    public void finished(){
        System.out.println("Count is: " + count);
    }
}
