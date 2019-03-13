package s.j.millington.sync.sync1;

import java.util.Scanner;

class Processor extends Thread{

   // private boolean running = true; //can sometimes be cached - so won't always see the change. Might assume running is always true to optimise
    private volatile boolean running = true; //prevents threads caching variables

    @Override
    public void run(){

        while(running){
            System.out.println("hello");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void shutdown(){
        running = false;
    }
}


public class App {

    public static void main(String[] args) {

        Processor proc1 = new Processor();
        proc1.start();

        System.out.println("Press return to stop");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        proc1.shutdown();
    }
}
