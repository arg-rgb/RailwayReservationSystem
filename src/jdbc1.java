import java.sql.*;

public class jdbc1 {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/train_reservation";

        String user = "postgres";
        String password = "argha";

        try{
            Connection con = DriverManager.getConnection(url,user,password);

            String sql = "insert into trains values(?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1,105);
            ps.setString(2, "Rajdhani");
            ps.setString(3, "Delhi");
            ps.setString(4, "Kolkata");
            ps.setInt(5,50);

            int rows = ps.executeUpdate();

            
            System.out.println("Rows inserted : " + rows);
            

            // Statement st = con.createStatement();
            // String sql1 = "select * from trains";
            // ResultSet rs = st.executeQuery(sql1);

            // while(rs.next()){
            //     int id = rs.getInt("id");
            //     String name = rs.getString("name");
            //     String source = rs.getString("source");
            //     String destination = rs.getString("destination");
            //     int seats = rs.getInt("available_seats");

            //     System.out.println(
            //         id + " | " +
            //         name + " | " +
            //         source + " -> " +
            //         destination + " | Seats : " +
            //         seats
            //     );
            // }

            con.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
