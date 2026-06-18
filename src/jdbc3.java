import java.sql.*;

public class jdbc3 {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/train_reservation";
        String user = "postgres";
        String password = "argha";


        try{
            Connection con = DriverManager.getConnection(url,user,password);

            String sql = "select * from trains where id = ?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1,101);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String source = rs.getString("source");
                String destination = rs.getString("destination");
                int seats = rs.getInt("available_seats");


                System.out.println(
                    "id : " + id + " | name : " + name + " | source : "+ source + " | destination : " + destination + " | seats : " + seats
                );
            }
            con.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
