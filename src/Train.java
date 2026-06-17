public class Train {
    String id;
    String name;
    String source;
    String destination;
    int availableSeats;

    //constructor
    public Train(String id,String name, String source, String destination, int availableSeats){
        this.id = id;
        this.name = name;
        this.source = source;
        this.destination = destination;
        this.availableSeats = availableSeats;
    }

    //object printing
    @Override
    public String toString(){
        return id + " " + name +" "+ source +" "+destination + " " + availableSeats;
    }
}
