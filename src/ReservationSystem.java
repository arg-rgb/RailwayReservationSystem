import java.util.HashMap;

public class ReservationSystem {
    private HashMap<Integer,Train> trains = new HashMap<>();
    private HashMap<Integer,Booking> bookings = new HashMap<>();

    public void addTrains(Train t){
        trains.put(t.getId() ,t);
        System.out.println("Train added successfully");
    }

    public void viewTrains(){
        for(HashMap.Entry<Integer,Train> entry : trains.entrySet()){
            System.out.println(entry.getValue());
        }
    }

    public void bookSeat(int trainid, String passengerName){
        if(trains.containsKey(trainid)){
            Train t = trains.get(trainid);
            if(t.getAvailableSeats() > 0){
                t.bookSeat();
                Booking booking = new Booking((bookings.size()+1), passengerName, trainid);
                bookings.put(booking.getBookId(), booking);
                booking.setCancelled(false);
                System.out.println("Booking Successful...\nBooking id : " + booking.getBookId());
            }
            else{
                System.out.println("No available seats...Sorry ;) ");
            }
        }
        else{
            System.out.println("Train Not Found...");
        }
    }

    public void viewBookings(){
        if(bookings.isEmpty()){
            System.out.println("No Bookings found...");
            return;
        }

        for(HashMap.Entry<Integer,Booking> entry : bookings.entrySet()){
            System.out.println(entry.getValue().toString()); //showing the booking....
        }
    }

    public void cancelBooking(int bookingId){
        if(bookings.containsKey(bookingId)){
            Booking cancel = bookings.get(bookingId);
            int id = cancel.getTrainId();
            Train t = trains.get(id);
            t.releaseSeat();
            cancel.setCancelled(true);
            // bookings.remove(bookingId);  not removed for history...
            System.out.println("Booking Cancelled successfully...");
        }
        else{
            System.out.println("Booking id not found... :)");
        }
    }
}


