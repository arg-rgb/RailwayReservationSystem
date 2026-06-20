public class Booking {
    private int bookingId;
    private String passengerName;
    private int trainId;

    private String status;

    public Booking(int bookingId,String passengerName,int trainId, String status){
        this.bookingId = bookingId;
        this.passengerName = passengerName;
        this.trainId = trainId;
        this.status = status;
    }

    public int getBookId(){
        return this.bookingId;
    }

    public int getTrainId(){
        return this.trainId;
    }

    public String getStatus(){
        return this.status;
    }

    public void setStatus(String sts){
        this.status = sts;
    }

    // public void setCancelled(boolean sts){
    //     this.isCancelled = sts;
    // }

    // public boolean isCancelled(){
    //     return isCancelled;
    // }

    public String getPassengerName(){
        return this.passengerName;
    }

    @Override
    public String toString(){
        return "\nBooking id : " + bookingId + "\n"+ "Passenger Name : " + passengerName + "\n" + "Train id :" +trainId + status;
    }
}
