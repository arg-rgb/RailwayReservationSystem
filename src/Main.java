

public class Main {
    public static void main(String[] args) {
        Train t1 = new Train(101,"Howrah","Kolkata","Delhi",1);

        ReservationSystem rs = new ReservationSystem();
        rs.addTrains(t1);
        
        Thread thread1 = new Thread(()->{
            rs.bookSeat(101, "user1");
        });

        Thread thread2 = new Thread(()->{
            rs.bookSeat(101, "user2");
        });

        Thread thread3 = new Thread(()->{
            rs.bookSeat(101, "user3");
        });

        thread3.start();
        thread2.start();
        thread1.start();

        try{
            thread1.join();
            thread2.join();
            thread3.join();
        }
        catch(InterruptedException e){

        }

        rs.viewBookings();
        rs.viewTrains();
    }
}
