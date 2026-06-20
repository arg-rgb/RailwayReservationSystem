

public class Main {
    public static void main(String[] args) {

        // TrainDAO dao = new TrainDAO();
        // // dao.addTrain(train);
        
        // // dao.viewTrains();
        // Train t = dao.getTrainById(303);

        // if(t != null){
        //     System.out.println(t);
        // }

        // dao.updateSeats(303, 85);

        // System.out.println(dao.getTrainById(303));
        BookingDao dao = new BookingDao();
        
        dao.cancelBooking(10263);
    }
}
