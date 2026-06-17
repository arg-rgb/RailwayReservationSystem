

public class Main {
    public static void main(String[] args) {
        Train t1 = new Train(101,"Howrah","Kolkata","Delhi",4);

        // t1.id = 101;
        // t1.name = "Howrah Express";
        // t1.source = "Kolkata";
        // t1.destination = "Delhi";
        // t1.availableSeats = 100;

        System.out.println(t1.getAvailableSeats());
        System.out.println(t1);     //object printing...


        ReservationSystem rs = new ReservationSystem();
        rs.addTrains(t1);
        rs.viewTrains();

        rs.bookSeat(101, "Argha");
        rs.bookSeat(101, "Ag");
        rs.bookSeat(101, "tojo");
        rs.bookSeat(101, "tojoGGG");
       
        rs.cancelBooking(3);
        rs.viewBookings();
    }
}
