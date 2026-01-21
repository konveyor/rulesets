package javax.management.j2ee;

import java.rmi.RemoteException;

public interface ManagementHome {
    Management create() throws RemoteException;
}

