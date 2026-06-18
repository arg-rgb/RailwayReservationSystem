import java.sql.*;

public class deleteBooking {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/train_reservation";
        String user = "postgres";
        String password = "argha";

        try{
            Connection con = DriverManager.getConnection(url,user,password);

            String sql = "delete from bookings where booking_id = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,999);

            int rows = ps.executeUpdate();

            System.out.println("Rows deleted : " + rows);
            con.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
