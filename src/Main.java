import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ReservationSystem reservationSystem = new ReservationSystem();
        TrainDAO trainDAO = new TrainDAO();
        BookingDao bookingDAO = new BookingDao();

        int choice = sc.nextInt();

        do{
            showMenu();

            choice = sc.nextInt();

            switch (choice) {

                case 0:
                    System.out.println("Thank you for using Railway Reservation System.");
                    break;
                case 1:
                    System.out.print("Enter Train ID : ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Train Name : ");
                    String name = sc.nextLine();

                    System.out.print("Enter Source : ");
                    String source = sc.nextLine();

                    System.out.print("Enter Destination : ");
                    String destination = sc.nextLine();

                    System.out.print("Enter Available Seats : ");
                    int seats = sc.nextInt();

                    Train train = new Train(id, name, source, destination, seats);

                    trainDAO.addTrain(train);
                    break;
                
                case 2: trainDAO.viewTrains(); break;

                case 3:
                    sc.nextLine();

                    System.out.print("Enter Source : ");
                    String src = sc.nextLine();

                    System.out.print("Enter Destination : ");
                    String dest = sc.nextLine();

                    System.out.print("Minimum Available Seats : ");
                    int minSeats = sc.nextInt();

                    trainDAO.searchTrain(src, dest, minSeats);
                    break;

                    case 4:
                        sc.nextLine();
                    
                        System.out.print("Enter Train ID : ");
                        int trainId = sc.nextInt();
                        sc.nextLine();
                    
                        System.out.print("Enter Passenger Name : ");
                        String passenger = sc.nextLine();
                    
                        reservationSystem.bookSeat(trainId, passenger);
                        break;

                    case 5:
                        System.out.print("Enter Booking ID : ");
                        int bookingId = sc.nextInt();
                        
                        reservationSystem.cancelBooking(bookingId);
                        break;

                    case 6:
                        System.out.print("Enter Booking ID : ");
                        int booking = sc.nextInt();
                        
                        bookingDAO.viewBooking(booking);
                        break;

                    case 7:
                        sc.nextLine();
                        
                        System.out.print("Enter Passenger Name : ");
                        String passengerName = sc.nextLine();
                        
                        bookingDAO.viewBookingsByPassenger(passengerName);
                        break;

                    case 8:
                        bookingDAO.viewBookingHistory();
                        break;

                    case 9:
                        bookingDAO.bookingStatistics();
                        break;

                    case 10:

                        System.out.println("1 for Ascending");
                        System.out.println("2 for Descending");
                    
                        System.out.print("Choose : ");
                        int order = sc.nextInt();
                    
                        if(order == 1){
                            trainDAO.viewTrainsSortedBySeats(true);
                        }
                        else{
                            trainDAO.viewTrainsSortedBySeats(false);
                        }
                    
                        break;

                    case 11:

                        System.out.print("Enter Limit : ");
                        int limit = sc.nextInt();
                    
                        System.out.print("Enter Offset : ");
                        int offset = sc.nextInt();
                    
                        trainDAO.viewTrains(limit, offset);
                    
                        break;

                    case 12:

                        System.out.println(
                            "Total Bookings : " +
                            bookingDAO.getTotalBookings()
                        );
                    
                        break;

                    case 13:

                        System.out.println(
                            "Cancelled Bookings : " +
                            bookingDAO.getCancelledBookings()
                        );
                    
                        break;

                    case 14:

                        System.out.println(
                            "Total Available Seats : " +
                            trainDAO.getTotalAvailableSeats()
                        );
                    
                        break;

                    default:
                        System.out.println("Invalid Choice!");
            }
        }while(choice != 0);

        sc.close();
    }

    public static void showMenu() {
        System.out.println("\n========== RAILWAY RESERVATION SYSTEM ==========");
        System.out.println("1. Add Train");
        System.out.println("2. View All Trains");
        System.out.println("3. Search Train");
        System.out.println("4. Book Seat");
        System.out.println("5. Cancel Booking");
        System.out.println("6. View Booking");
        System.out.println("7. View Passenger Bookings");
        System.out.println("8. View Booking History");
        System.out.println("9. Booking Statistics");
        System.out.println("10. View Trains Sorted");
        System.out.println("11. View Trains (Pagination)");
        System.out.println("12. Total Bookings");
        System.out.println("13. Cancelled Bookings");
        System.out.println("14. Total Available Seats");
        System.out.println("0. Exit");
        System.out.print("\nEnter your choice: ");
    }
}
