import java.sql.*;

public class BookingDao {
    public void addBooking(Booking booking){
        try {
            Connection con = DatabaseManager.getConnection();
            String sql = "INSERT INTO bookings(booking_id, passenger_name, train_id, status) VALUES (?, ?, ?, ?)";
            
            PreparedStatement ps = con.prepareStatement(sql);

            BookingStatus sts = booking.getStatus();

            ps.setInt(1,booking.getBookId());
            ps.setString(2, booking.getPassengerName());
            ps.setInt(3,booking.getTrainId());
            ps.setString(4, sts.name()); // name() is for the enum

            int rows = ps.executeUpdate();

            System.out.println("Rows inserted : " + rows);

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addBooking(Connection con ,Booking booking){
        try {
            String sql = "INSERT INTO bookings(booking_id, passenger_name, train_id, status) VALUES (?, ?, ?, ?)";
            
            PreparedStatement ps = con.prepareStatement(sql);

            BookingStatus sts = booking.getStatus();

            ps.setInt(1,booking.getBookId());
            ps.setString(2, booking.getPassengerName());
            ps.setInt(3,booking.getTrainId());
            ps.setString(4, sts.name()); // name() is for the enum

            int rows = ps.executeUpdate();

            System.out.println("Rows inserted : " + rows);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewBookings(){
        try (Connection con = DatabaseManager.getConnection()){
            
            String sql = "select * from bookings";

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int booking_id = rs.getInt("booking_id");
                String passengerName = rs.getString("passenger_name");
                int train_id = rs.getInt("train_id");
                BookingStatus status = BookingStatus.valueOf(rs.getString("status"));

                System.out.println(
                    "booking id : " + booking_id +
                    "   Passenger Name : " + passengerName +
                    "   Train id : " + train_id +
                    "   Status : " + status
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Booking getBookingById(Connection con,int booking_id){

        String sql = "select * from bookings where booking_id = ?";
        try(PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1,booking_id);

            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    Booking booking = new Booking(rs.getInt("booking_id"), rs.getString("passenger_name"), rs.getInt("train_id"),BookingStatus.valueOf(rs.getString("status")));
                    return booking;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void cancelBooking(Connection con,int booking_id){
        try {
            String sql = "update bookings set status = 'CANCELLED' where booking_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,booking_id);

            int rows = ps.executeUpdate();

            System.out.println("Rows updated : " + rows);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getTotalBookings(){
        String sql = "select count(*) from bookings";
        try(Connection con = DatabaseManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    return rs.getInt("count");  // or rs.getInt(1) means the column number 1
                }
            }
        catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public int getCancelledBookings(){
        String sql = "select count(*) from bookings where status = ?";
        try(Connection con = DatabaseManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
                ps.setString(1,BookingStatus.CANCELLED.name());
                try(ResultSet rs = ps.executeQuery()){
                    if(rs.next()){
                        int bookings = rs.getInt(1);
                        return bookings;
                    }
                }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return 0;
    }

    public void viewBookingsByPassenger(String passenger_name){
        String sql = "select * from bookings where passenger_name = ?";
        try (Connection con = DatabaseManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
                ps.setString(1,passenger_name);
                try(ResultSet rs = ps.executeQuery()){
                    boolean f = false;
                    while(rs.next()){
                        f = true;
                        Booking b = mapBooking(rs);
                        System.out.println(b);
                    }
                    if(!f){
                        System.out.println("No bookings found for passenger : " + passenger_name);
                    }
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Booking mapBooking(ResultSet rs) throws SQLException{
        return new Booking(rs.getInt("booking_id"),rs.getString("passenger_name"), rs.getInt("train_id"), BookingStatus.valueOf(rs.getString("status")));
    }

    public void viewBookingHistory(){
        String sql = "select b.booking_id, b.passenger_name , t.name , t.source , t.destination , b.status from bookings b join trains t on b.train_id = t.id";
        try (Connection con = DatabaseManager.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            boolean f = false;
            while(rs.next()){
                f = true;
                System.out.println("\nBooking id : " + rs.getInt("booking_id") + "\nPassenger Name : " + rs.getString("passenger_name") + "\nTrain Name : " + rs.getString("name") + "\nSource : " + rs.getString("source") + "\nDestination : " + rs.getString("destination") + "\nStatus :" +rs.getString("status"));
            }
            if(!f){
                System.out.println("No booking found...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void bookingStatistics(){
        String sql = "select status,count(*) from bookings group by status";   
        try (Connection con = DatabaseManager.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            boolean f = false;
            while(rs.next()){
                f = true;
                String sts = rs.getString("status");
                int count = rs.getInt(2); //2nd column pr "count" is the 2nd column
                System.out.println(sts + " : " + count + "\n");
            }

            if(!f){
                System.out.println("No Bookings found for statistics...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
