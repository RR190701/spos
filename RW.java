import java.util.concurrent.Semaphore;
import java.util.Scanner;

public class RW{
    static Semaphore mutex= new Semaphore(1);
    static Semaphore wrt=new Semaphore(1);
     static int rc=0;
     static String data = "Hello";
     Scanner sc=new Scanner(System.in);

     
    static class Writer implements Runnable{
        public void run(){
            try{
               wrt.acquire();
               System.out.println(Thread.currentThread().getName()+" writing!!");
               data = "changed";
               Thread.sleep(4000);
               System.out.println(Thread.currentThread().getName()+" done writing!!");
               wrt.release();
            }

            catch(InterruptedException e){
                System.out.println(e.getMessage());
            }
        }
    }
    static class Reader implements Runnable{
        public void run(){
            try{
                mutex.acquire();
                rc++;
                if(rc==1) wrt.acquire();
                mutex.release();
                System.out.println(Thread.currentThread().getName()+" is reading**" + data);
                Thread.sleep(4000);
                System.out.println(Thread.currentThread().getName()+" done reading**");
                rc--;
                mutex.acquire();
                if(rc==0) wrt.release();
                mutex.release();
            }
            catch(InterruptedException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws Exception{
        Reader r=new Reader();
        Thread r1 = new Thread(r);
        r1.setName("R1");
        Thread r2=new Thread(r);
        r2.setName("R2");
        Thread r3=new Thread(r);
        r3.setName("R3");

        Writer w=new Writer();
        Thread w1=new Thread(w);
        w1.setName("W1");
        Thread w2=new Thread(w);
        w2.setName("W2");
        Thread w3=new Thread(w);
        w3.setName("W3");

        r1.start();
        r2.start();
        w3.start();
        w1.start();
        r3.start();
        w3.start();


    }

}
 