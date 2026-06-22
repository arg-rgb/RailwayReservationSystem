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
        try {
            Connection con = DatabaseManager.getConnection();
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
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Booking getBookingById(Connection con,int booking_id){
        try {
            String sql = "select * from bookings where booking_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1,booking_id);

            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Booking booking = new Booking(rs.getInt("booking_id"), rs.getString("passenger_name"), rs.getInt("train_id"),BookingStatus.valueOf(rs.getString("status")));
                con.close();
                return booking;
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
}
