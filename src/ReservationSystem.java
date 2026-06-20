import java.util.concurrent.locks.ReentrantLock;

public class ReservationSystem {
    private TrainDAO trainDAO = new TrainDAO();
    private BookingDao bookingDao = new BookingDao();

    private static int booking_counter = 10000;
    private ReentrantLock lock = new ReentrantLock();

    public void addTrains(Train t){
        trainDAO.addTrain(t);
    }

    public void viewTrains(){
        trainDAO.viewTrains();
    }

    public void bookSeat(int trainid, String passengerName){
        lock.lock();

        try{
            Train t = trainDAO.getTrainById(trainid);
            if(t != null){
                if(t.getAvailableSeats() > 0){
                    trainDAO.updateSeats(trainid, t.getAvailableSeats() - 1);

                    Booking b = new Booking(booking_counter++, passengerName, trainid, "CONFIRMED");

                    bookingDao.addBooking(b);

                    System.out.println("Booking successFul...\nBooking id : " + b.getBookId());
                }
                else{
                    System.out.println("No available seats...");
                }
            }
            else{
                System.out.println("Train Not Found...");
            }
        }
        finally{
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
        if(b.getStatus().equals("CANCELLED")){
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


