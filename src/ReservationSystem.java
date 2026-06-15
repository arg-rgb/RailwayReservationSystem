import java.util.ArrayList;

public class ReservationSystem {
    ArrayList<Train> trains = new ArrayList<>();

    public void addTrains(Train t){
        trains.add(t);
        System.out.println("Train added successfully");
    }

    public void viewTrains(){
        for(Train t : trains){
            System.out.println(t);
        }
    }
}


