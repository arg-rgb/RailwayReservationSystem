public class Train {
    private int id;
    private String name;
    private String source;
    private String destination;
    private int availableSeats;

    //constructor
    public Train(int id,String name, String source, String destination, int availableSeats){
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

    public void setAvailableSeats(int seat){
        this.availableSeats = seat;
    }

    public int getId(){
        return this.id;
    }
    
    public int getAvailableSeats(){
        return this.availableSeats;
    }

    public void bookSeat(){
        availableSeats--;
    }

    public void releaseSeat(){
        availableSeats++;
    }

    public String getSource(){
        return this.source;
    }

    public String getDestination(){
        return this.destination;
    }

    public String getName(){
        return this.name;
    }
}
