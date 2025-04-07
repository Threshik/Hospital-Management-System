package exception;

public class AppointmentNotFoundException extends Exception{
    public AppointmentNotFoundException(String message){
        super(message);
    }
}
