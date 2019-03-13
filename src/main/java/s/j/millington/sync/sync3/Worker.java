package s.j.millington.sync.sync3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Worker {

    private Random random = new Random();

    //it's good practice to declare separate lock objects.
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    private List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();

    //sync on the method signature acquires the intrinsic lock on the whole Worker object. - NOT just the one method.
    public void stageOne() {

        synchronized (lock1) {
            try {
                Thread.sleep(1);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            list1.add(random.nextInt(100));
        }
    }

    public void stageTwo(){

        synchronized (lock2) {
            try {
                Thread.sleep(1);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            list2.add(random.nextInt(100));
        }
    }

    public void process(){

        for(int i = 0; i < 1000; i++){
            stageOne();
            stageTwo();
        }
    }

    public void main(){
        System.out.println("Starting ...");
        long start = System.currentTimeMillis();

        Thread t1 = new Thread(this::process);
        Thread t2 = new Thread(this::process);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();

        System.out.println("Time taken: " + (end-start) + "ms");
        System.out.println("List One: " + list1.size());
        System.out.println("List Two: " + list2.size());
    }
}
