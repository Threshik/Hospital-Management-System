package exception;

public class PatientNumberNotFoundException extends Exception {
    public PatientNumberNotFoundException(String message) {
        super(message);
        //System.out.println("Patient id not found");
    }
}
