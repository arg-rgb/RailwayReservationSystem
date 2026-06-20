import java.sql.*;

public class BookingDao {
    public void addBooking(Booking booking){
        try {
            Connection con = DatabaseManager.getConnection();
            String sql = "INSERT INTO bookings(booking_id, passenger_name, train_id, status) VALUES (?, ?, ?, ?)";
            
            PreparedStatement ps = con.prepareStatement(sql);

            String sts = (booking.isCancelled() ? "CANCELLED" : "CONFIRMED");

            ps.setInt(1,booking.getBookId());
            ps.setString(2, booking.getPassengerName());
            ps.setInt(3,booking.getTrainId());
            ps.setString(4, sts);

            int rows = ps.executeUpdate();

            System.out.println("Rows inserted : " + rows);

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewBookings(){
        try {
            Connection con = DatabaseManager.getConnection();
            String sql = "select * from bookings";

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int booking_id = rs.getInt("booking_id");
                String passengerName = rs.getString("passenger_name");
                int train_id = rs.getInt("train_id");
                String status = rs.getString("status");

                System.out.println(
                    "booking id : " + booking_id +
                    "   Passenger Name : " + passengerName +
                    "   Train id : " + train_id +
                    "   Status : " + status
                );

                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
