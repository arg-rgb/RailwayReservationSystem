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
}
