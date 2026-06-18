import java.sql.*;

public class insertBooking {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/train_reservation";
        String user = "postgres";
        String password = "argha";

        try{
            Connection con = DriverManager.getConnection(url,user,password);

            String sql = "insert into bookings values(?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1,919);
            ps.setString(2,"Argha Ghosh");
            ps.setInt(3, 201);
            ps.setString(4, "CONFIRMED");

            int rows = ps.executeUpdate();

            System.out.println("Row inserted : " + rows);

            con.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
