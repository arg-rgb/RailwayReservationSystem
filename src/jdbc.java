import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class jdbc {
    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/train_reservation";
        String user = "postgres";
        String password = "argha";

        try{
            Connection con = DriverManager.getConnection(url,user,password);
            Statement st = con.createStatement();

            String sql = "select * from trains";
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String source = rs.getString("source");
                String destination = rs.getString("destination");
                int seats = rs.getInt("available_seats");

                System.out.println(
                    id + " | " +
                    name + " | " +
                    source + " -> " +
                    destination + " | Seats : " +
                    seats
                );
            }

            con.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}