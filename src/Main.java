

public class Main {
    public static void main(String[] args) {
        Train t1 = new Train("101","Howrah","Kolkata","Delhi",100);

        // t1.id = 101;
        // t1.name = "Howrah Express";
        // t1.source = "Kolkata";
        // t1.destination = "Delhi";
        // t1.availableSeats = 100;

        System.out.println(t1.availableSeats);
        System.out.println(t1);     //object printing...


        ReservationSystem rs = new ReservationSystem();
        rs.addTrains(t1);
        rs.viewTrains();

        Booking b1 = new Booking("1", "Argha", "001");
        System.out.println(b1.passengerName);
    }
}
