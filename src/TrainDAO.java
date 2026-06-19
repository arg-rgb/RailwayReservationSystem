import java.sql.*;


public class TrainDAO {
    public void addTrain(Train train){
        try {
            Connection con = DatabaseManager.getConnection();

            String sql = "insert into trains values(?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1,train.getId());
            ps.setString(2,train.getName());
            ps.setString(3,train.getSource());
            ps.setString(4,train.getDestination());
            ps.setInt(5,train.getAvailableSeats());

            int rows = ps.executeUpdate();

            System.out.println("Rows inserted : " + rows);

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void viewTrains(){
        try {
            Connection con = DatabaseManager.getConnection();

            String sql = "select * from trains";
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

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

                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
