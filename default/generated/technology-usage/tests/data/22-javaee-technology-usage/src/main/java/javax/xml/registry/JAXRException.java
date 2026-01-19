package javax.xml.registry;

public class JAXRException extends Exception {
    public JAXRException() {
        super();
    }
    
    public JAXRException(String message) {
        super(message);
    }
    
    public JAXRException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public JAXRException(Throwable cause) {
        super(cause);
    }
}

