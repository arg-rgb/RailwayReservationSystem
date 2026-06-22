import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.locks.ReentrantLock;

public class ReservationSystem {
    private TrainDAO trainDAO = new TrainDAO();
    private BookingDao bookingDao = new BookingDao();

    private static int booking_counter = 30000;
    private ReentrantLock lock = new ReentrantLock();

    Connection con = null;

    public void addTrains(Train t){
        trainDAO.addTrain(t);
    }

    public void viewTrains(){
        trainDAO.viewTrains();
    }


    public void bookSeat(int trainid, String passengerName){
        lock.lock();
        Connection con = null;
        try {
            con = DatabaseManager.getConnection();
            con.setAutoCommit(false);

            Train t = trainDAO.getTrainById(con,trainid);

            if(t == null){
                throw new TrainNotFoundException("Train with id : " + trainid + " is not found..");
            }
            
            if(t.getAvailableSeats() <= 0){
                throw new NoSeatsAvailableException("No available seats...in train : " + trainid);
            }

            trainDAO.updateSeats(con,trainid, t.getAvailableSeats()-1);
            // int x = 10/0;        for only testing the commiting of the transaction
            Booking b = new Booking(booking_counter++, passengerName, trainid,BookingStatus.CONFIRMED);
            bookingDao.addBooking(con,b);

            con.commit();
            System.out.println("Booking successFul...\nBooking id : " + b.getBookId());

        }
        catch(NoSeatsAvailableException e){
            rollback(con);
            System.out.println(e.getMessage());                                  //exception means roll back
        }

        catch(TrainNotFoundException e){
            rollback(con);
            System.out.println(e.getMessage());
        }

        catch (Exception e) {
            rollback(con);
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

    private void rollback(Connection con){
        try {
            if(con != null){
                con.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();    
        }
    }

    public void viewBookings(){
        bookingDao.viewBookings();
    }

    public void cancelBooking(int bookingId){
        lock.lock();
        Connection con = null;

        try {
            con = DatabaseManager.getConnection();
            con.setAutoCommit(false);

            Booking b = bookingDao.getBookingById(con,bookingId);
            
            if(b == null){
                throw new BookingNotFoundException(
                    "Booking with id " + bookingId + " not found"
                );
            }

            if(b.getStatus() == BookingStatus.CANCELLED){
                throw new BookingAlreadyCancelledException(
                    "Booking already cancelled"
                );
            }

            Train t = trainDAO.getTrainById(con,b.getTrainId());
            if(t == null){
                throw new TrainNotFoundException("Train with id : " + b.getTrainId() + " is not found..");
            }
            trainDAO.updateSeats(con, b.getTrainId(), t.getAvailableSeats() + 1);
            // int x = 10/0;   for only testing the transaction...
            bookingDao.cancelBooking(con,bookingId);

            con.commit();
            System.out.println("Booking Cancelled Successfully...");

        }
        catch(BookingAlreadyCancelledException e){
            rollback(con);
            System.out.println(e.getMessage());
        }
        catch(BookingNotFoundException e){
            rollback(con);
            System.out.println(e.getMessage());
        }
        catch(TrainNotFoundException e){
            rollback(con);
            System.out.println(e.getMessage());
        }
        catch (Exception e) {
            rollback(con);
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

    public void searchTrain(String source, String destination){
        trainDAO.searchTrain(source, destination);
    }
}


