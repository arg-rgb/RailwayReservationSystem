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
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Train getTrainById(int train_id){
        try {
            Connection con = DatabaseManager.getConnection();
            String sql = "select * from trains where id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,train_id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String source = rs.getString("source");
                String destination = rs.getString("destination");
                int seats = rs.getInt("available_seats");

                Train train = new Train(id, name, source, destination, seats);
                con.close();
                return train;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public Train getTrainById(Connection con ,int train_id){
        try {
            String sql = "select * from trains where id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,train_id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String source = rs.getString("source");
                String destination = rs.getString("destination");
                int seats = rs.getInt("available_seats");

                Train train = new Train(id, name, source, destination, seats);
                return train;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void updateSeats(int id,int seats){
        try {
            Connection con = DatabaseManager.getConnection();

            String sql = "update trains set available_seats = ? where id = ?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1,seats);
            ps.setInt(2,id);

            int rows = ps.executeUpdate();

            System.out.println("rows updated : " + rows);

            con.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateSeats(Connection con,int id,int seats){
        try {
            String sql = "update trains set available_seats = ? where id = ?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1,seats);
            ps.setInt(2,id);

            int rows = ps.executeUpdate();

            System.out.println("rows updated : " + rows);

            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchTrain(String source, String destination){
        try {
            Connection con = DatabaseManager.getConnection();
            String sql = "select * from trains where source = ? and destination = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, source);
            ps.setString(2, destination);

            ResultSet rs = ps.executeQuery();
            boolean found = false;
            while(rs.next()){
                found = true;
                Train t = new Train(rs.getInt("id"),rs.getString("name"),rs.getString("source"),rs.getString("destination"), rs.getInt("available_seats"));
                System.out.println(t);                
            }
            if(!found){
                System.out.println("No trains found...");
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
