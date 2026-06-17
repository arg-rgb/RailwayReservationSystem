import java.util.concurrent.locks.ReentrantLock;

public class deadlock {
    public static void main(String[] args) {
        ReentrantLock lock1 = new ReentrantLock();
        ReentrantLock lock2 = new ReentrantLock();

        Thread t1 = new Thread(()->{
            lock1.lock();
            System.out.println("Thread 1 aquired lock 1");

            try{
                Thread.sleep(100);
                System.out.println("1 waiting for lock 2");

                lock2.lock();

                System.out.println("1 aquires lock 2");

            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(()->{
            lock2.lock();
            System.out.println("Thread 2 aquired lock 2");

            try{
                Thread.sleep(100);
                System.out.println("2 waiting for lock 1");

                lock1.lock();

                System.out.println("2 aquires lock 1");

            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
    }
}
