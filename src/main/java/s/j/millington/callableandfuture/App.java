package s.j.millington.callableandfuture;

import java.util.Random;
import java.util.concurrent.*;

public class App {

    public static void main(String[] args) {

        ExecutorService service = Executors.newCachedThreadPool();

        //can use a ? for the type, or a class such as Integer.

        Future<?> future =  service.submit(() -> {
            Random random = new Random();
            int duration = random.nextInt(4000);

            if(duration > 2000){
                throw new Exception("I'm greater than 2000");
            }

            System.out.println("Starting");

            try {
                Thread.sleep(duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            System.out.println("Ending");

            return duration;
        });

        service.shutdown();

       // future.cancel(true); //cancel processing
       // future.isDone(); //is done
       // future.isCancelled(); //is cancelled


        try {
            System.out.println("Result is: " + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.out.println(e.getMessage());
        }

    }
}
