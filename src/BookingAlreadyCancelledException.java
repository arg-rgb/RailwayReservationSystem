public class BookingAlreadyCancelledException extends ReservationException{
    public BookingAlreadyCancelledException(String message){
        super(message);
    }
}