import java.util.ArrayList;

public class ReservationSystem {
    ArrayList<Train> trains = new ArrayList<>();
    ArrayList<Booking> bookings = new ArrayList<>();

    public void addTrains(Train t){
        trains.add(t);
        System.out.println("Train added successfully");
    }

    public void viewTrains(){
        for(Train t : trains){
            System.out.println(t);
        }
    }

    public void bookseat(String trainid, String passengerName){
        boolean train_found = false;
        for(Train t : trains){
            if(trainid == t.id){
                train_found = true;
                if(t.availableSeats > 0){
                    t.availableSeats--;
                    Booking booking = new Booking(bookings.size()+1 + "", passengerName, trainid); //converting booking id to string and then add...
                    bookings.add(booking);

                    System.out.println("Booking Successful...");
                }
                else{
                    System.out.println("No available seats...");
                }
            }
        }
        if(train_found == false) System.out.println("train not found...");
    }
}


