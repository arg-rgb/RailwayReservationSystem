public class Booking {
    private int bookingId;
    private String passengerName;
    private int trainId;

    private boolean isCancelled;

    public Booking(int bookingId,String passengerName,int trainId){
        this.bookingId = bookingId;
        this.passengerName = passengerName;
        this.trainId = trainId;
        this.isCancelled = true;
    }

    public int getBookId(){
        return this.bookingId;
    }

    public int getTrainId(){
        return this.trainId;
    }

    public void setCancelled(boolean sts){
        this.isCancelled = sts;
    }

    @Override
    public String toString(){
        return "\nBooking id : " + bookingId + "\n"+ "Passenger Name : " + passengerName + "\n" + "Train id :" +trainId + (isCancelled ? "CANCELLED" : "CONFIRMED");
    }
}
