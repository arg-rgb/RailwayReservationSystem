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

                try{
                    System.out.println("1 aquires lock 2");
                }
                finally{
                    lock2.unlock();
                }
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
            finally{
                lock1.unlock();
            }
        });

        Thread t2 = new Thread(()->{
            lock1.lock();
            System.out.println("Thread 2 aquired lock 2");

            try{
                Thread.sleep(100);
                System.out.println("2 waiting for lock 1");

                lock2.lock();

                try{
                    System.out.println("2 aquires lock 1");
                }
                finally{
                    lock2.unlock();
                }

            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
            finally{
                lock1.unlock();
            }
        });

        t1.start();
        t2.start();
    }
}
