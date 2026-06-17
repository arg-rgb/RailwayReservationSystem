import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;

public class jdbc {
    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/train_reservation";
        String user = "postgres";
        String password = "argha";

        try{
            Connection con = DriverManager.getConnection(url,user,password);
            Statement st = con.createStatement();

            String sql = "insert into trains(id,name,source,destination,available_seats )" + "values(101,'Howrah Express','Kolkata','Delhi',10)";
            int rows = st.executeUpdate(sql);

            System.out.println("Rows inserted : " + rows);

            con.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}