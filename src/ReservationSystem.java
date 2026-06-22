import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.locks.ReentrantLock;

public class ReservationSystem {
    private TrainDAO trainDAO = new TrainDAO();
    private BookingDao bookingDao = new BookingDao();

    private static int booking_counter = 10000;
    private ReentrantLock lock = new ReentrantLock();

    Connection con = null;

    public void addTrains(Train t){
        trainDAO.addTrain(t);
    }

    public void viewTrains(){
        trainDAO.viewTrains();
    }

    // public void bookSeat(int trainid, String passengerName){
    //     // lock.lock();

    //     try{
    //         Train t = trainDAO.getTrainById(trainid);
    //         if(t != null){
    //             if(t.getAvailableSeats() > 0){
    //                 trainDAO.updateSeats(trainid, t.getAvailableSeats() - 1);

    //                 Booking b = new Booking(booking_counter++, passengerName, trainid, BookingStatus.CONFIRMED);

    //                 bookingDao.addBooking(b);

    //                 System.out.println("Booking successFul...\nBooking id : " + b.getBookId());
    //             }
    //             else{
    //                 System.out.println("No available seats...");
    //             }
    //         }
    //         else{
    //             System.out.println("Train Not Found...");
    //         }
    //     }
    //     finally{
    //         lock.unlock();
    //     }
    // }

    public void bookSeat(int trainid, String passengerName){
        lock.lock();
        Connection con = null;
        try {
            con = DatabaseManager.getConnection();
            con.setAutoCommit(false);

            Train t = trainDAO.getTrainById(con,trainid);

            if(t == null){
                System.out.println("No train found");
                return;
            }
            
            if(t.getAvailableSeats() <= 0){
                System.out.println("No seats available..");
                return;
            }

            trainDAO.updateSeats(con,trainid, t.getAvailableSeats()-1);
            int x = 10/0;
            Booking b = new Booking(booking_counter++, passengerName, trainid,BookingStatus.CONFIRMED);
            bookingDao.addBooking(con,b);

            con.commit();
            System.out.println("Booking successFul...\nBooking id : " + b.getBookId());

        } catch (Exception e) {
            try {
                if(con != null){
                    con.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
        finally{
            try {
                if(con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            lock.unlock();
        }
    }

    public void viewBookings(){
        bookingDao.viewBookings();
    }

    public void cancelBooking(int bookingId){
        Booking b = bookingDao.getBookingById(bookingId);

        if(b == null){
            System.out.println("Booking not found...");
            return;
        }
        if(b.getStatus() == BookingStatus.CANCELLED){
            System.out.println("Booking Already Cancelled...");
            return;
        }


        Train t = trainDAO.getTrainById(b.getTrainId());
        if(t == null){
            System.out.println("Train not found...");
            return;
        }
        trainDAO.updateSeats(b.getTrainId(), t.getAvailableSeats() + 1);

        bookingDao.cancelBooking(bookingId);
        System.out.println("Booking Cancelled SuccessFully...");
    }
}


