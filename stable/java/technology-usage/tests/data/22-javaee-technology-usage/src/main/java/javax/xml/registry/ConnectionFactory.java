package javax.xml.registry;

public abstract class ConnectionFactory {
    public static ConnectionFactory newInstance() {
        // Stub implementation for compilation
        throw new UnsupportedOperationException("ConnectionFactory implementation provided by application server");
    }
    
    public abstract Connection createConnection() throws JAXRException;
}

