package s.j.millington.sync.sync2;

public class App {

    private int count = 0; //that won't work by itself - need to synchronize a method or the field itself
    //private AtomicInteger count = new AtomicInteger(0); //this will work too!

    //Every object in java has an intrinsic lock, or mutex. Synchronize uses the lock to stop access from other threads

    public static void main(String[] args) {
        App app = new App();
        app.doWork();

    }

    public synchronized void increment(){
        count++;
    }

    public void doWork(){

        Thread t1 = new Thread(() -> {
            for(int i = 0; i < 10000; i++){
                //count++;
                //count.getAndIncrement();
                increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for(int i = 0; i < 10000; i++){
               // count++;
               // count.getAndIncrement();
                increment();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join(); //waits for t1 to finish
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("thread count is " + count);

    }
}
