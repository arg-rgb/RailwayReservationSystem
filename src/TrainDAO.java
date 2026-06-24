import java.sql.*;


public class TrainDAO {
    public void addTrain(Train train){
        try(Connection con = DatabaseManager.getConnection()) {
            String sql = "insert into trains values(?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1,train.getId());
            ps.setString(2,train.getName());
            ps.setString(3,train.getSource());
            ps.setString(4,train.getDestination());
            ps.setInt(5,train.getAvailableSeats());

            int rows = ps.executeUpdate();

            System.out.println("Rows inserted : " + rows);

            //con.close();  don't need bcz try with resources auto closes the connection 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void viewTrains(){
        try (Connection con = DatabaseManager.getConnection()){
            String sql = "select * from trains";
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Train t = mapTrain(rs);
                System.out.println(t);
            }
        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Train getTrainById(int train_id){
        try {
                Connection con = DatabaseManager.getConnection();
                Train train = getTrainById(con,train_id);
                con.close();
                return train;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public Train getTrainById(Connection con ,int train_id){
        String sql = "select * from trains where id = ?";

        try(PreparedStatement ps = con.prepareStatement(sql);) {
            
            ps.setInt(1,train_id);

            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    Train train = mapTrain(rs);
                    return train;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void updateSeats(int id,int seats){
        try {
            Connection con = DatabaseManager.getConnection();

            updateSeats(con,id, seats);
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
        String sql = "select * from trains where source = ? and destination = ?";

        try(Connection con = DatabaseManager.getConnection() ;
            PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, source);
            ps.setString(2, destination);

            boolean found = false;
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    found = true;
                    Train t = mapTrain(rs);
                    System.out.println(t);                
                }
            }
            if(!found){
                System.out.println("No trains found...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchTrain(String source, String destination,int seats){
        if(seats < 0){
            System.out.println("Seats cannot be negative...");
            return;
        }

        String sql = "select * from trains where source = ? and destination = ? and available_seats >= ?";

        try(Connection con = DatabaseManager.getConnection() ;
            PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, source);
            ps.setString(2, destination);
            ps.setInt(3,seats);

            boolean found = false;
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    found = true;
                    Train t = mapTrain(rs);
                    System.out.println(t);                
                }
            }
            if(!found){
                System.out.println("No trains found...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Train mapTrain(ResultSet rs) throws SQLException{
        return new Train(rs.getInt("id"), rs.getString("name"),rs.getString("source"),rs.getString("destination"), rs.getInt("available_seats"));
    }


    public int getTotalAvailableSeats(){
        String sql = "select sum(available_seats) from trains";
        try(Connection con = DatabaseManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
            
            if(rs.next()){
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void viewTrainsSortedBySeats(){
        String sql = "select * from trains order by available_seats desc";
        try (Connection con = DatabaseManager.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            boolean f = false;
            while(rs.next()){
                f= true;
                Train t = mapTrain(rs);
                System.out.println(t);
            }

            if(!f){
                System.out.println("No Trains Found...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewTrainsSortedBySeats(boolean ascending){
        String sql;
        if(ascending){
            sql = "select * from trains order by available_seats";
        }else{
            sql = "select * from trains order by available_seats desc";
        }
        try (Connection con = DatabaseManager.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            boolean f = false;
            while(rs.next()){
                f= true;
                Train t = mapTrain(rs);
                System.out.println(t);
            }

            if(!f){
                System.out.println("No Trains Found...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void viewTrains(int limit, int offset){  //pagination query...  !!!   //offset skip that number of values then return but LIMIT means how many tuples to return 
        String sql = "select * from trains order by id limit ? offset ?";
        try (Connection con = DatabaseManager.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1,limit);
            ps.setInt(2,offset);

            try (ResultSet rs = ps.executeQuery()) {
                boolean f = false;
                while (rs.next()) {
                    f = true;
                    Train t = mapTrain(rs);
                    System.out.println(t);
                }
                if(!f){
                    System.out.println("No trains found...");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
