package s.j.millington.producerconsumer.notify;

import java.util.Scanner;

public class Processor {



    public void produce() throws InterruptedException{
        synchronized (this){ // on the processor object first
            //runs first and waits, losing control of lock.
            System.out.println("Producer thread running");
            wait();
            //this will run after notify used
            System.out.println("Resumed");
        }
    }

    public void consume() throws InterruptedException{

        Scanner scanner = new Scanner(System.in);
        Thread.sleep(2000);

        synchronized (this){
            //this runs because produce has waited.
            System.out.println("Waiting for return key");
            scanner.nextLine();
            System.out.println("Return key pressed");
            notify();
            System.out.println("Sleeping 5 seconds");
            Thread.sleep(5000);
            System.out.println("Slept. Resuming..");
        }
    }
}
