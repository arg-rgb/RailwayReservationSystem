public class Booking {
    private int bookingId;
    private String passengerName;
    private int trainId;
    private BookingStatus status;

    public Booking(int bookingId,String passengerName,int trainId, BookingStatus status){
        this.bookingId = bookingId;
        this.passengerName = passengerName;
        this.trainId = trainId;
        this.status = status;
    }

    public void setBookId(int id){
        this.bookingId = id;
    }

    public Booking(String passengerName,int trainId, BookingStatus status){
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

    public BookingStatus getStatus(){
        return this.status;
    }

    public void setStatus(BookingStatus sts){
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
        return "\nBooking id : " + String.format("%06d", bookingId)+ "\n"+ "Passenger Name : " + passengerName + "\n" + "Train id :" +trainId + "\nStatus : "+ status;
    }
}
