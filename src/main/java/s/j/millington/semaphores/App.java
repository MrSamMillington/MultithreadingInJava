package s.j.millington.semaphores;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class App {

    public static void main(String[] args) {


        ExecutorService executor = Executors.newCachedThreadPool(); //thread pool which created new threads with each submit, but tries to re-use threads

        for(int i = 0; i < 200; i++){
            executor.submit(() -> {
                Connection.getInstance().connect();
            });
        }

        executor.shutdown();

        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Finishing");
    }
}
