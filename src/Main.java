

public class Main {
    public static void main(String[] args) {
        
        Train train = new Train(303, "Howrah Burdwan Local", "Howrah","Burdwan", 95);

        TrainDAO dao = new TrainDAO();
        // dao.addTrain(train);
        
        dao.viewTrains();
    }
}
