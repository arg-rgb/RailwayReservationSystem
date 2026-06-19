

public class Main {
    public static void main(String[] args) {
        
        Train train = new Train(103, "Howrah Burdwan Chord", "Howrah","Howrah", 101);

        TrainDAO dao = new TrainDAO();
        dao.addTrains(train);
        
    }
}
