package s.j.millington.interrupting;

import java.util.Random;

public class App {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Starting");
        Thread t1 = new Thread(() -> {
            Random r = new Random();

            for(int i=0; i<1E8; i++){

//                if(Thread.currentThread().isInterrupted()){
//                    System.out.println("Interrupted");
//                    break;
//                }

                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    System.out.println("interrupted");
                    break;
                }

                Math.sin(r.nextDouble());

            }

        });
        t1.start();

        Thread.sleep(500);

        t1.interrupt(); //doesnt stop the thread, just sets a flag that says the thread has been interrupted the thread;
        //Thread.sleep will detect the interrupted

        t1.join();

        System.out.println("Finished");
    }
}
