import java.sql.*;

public class updateTrains {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/train_reservation";
        String user = "postgres";
        String password = "argha";


        try{
            Connection con = DriverManager.getConnection(url,user,password);

            String sql = "update trains set available_seats = ? where id = ?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, 2);
            ps.setInt(2, 101);

            int rows = ps.executeUpdate();

            System.out.println("row updated : " + rows);

            con.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
