package org.windup.examples.technology.connect;

import java.rmi.Remote;
import java.rmi.RemoteException;
import javax.ws.rs.Path;

@Path("/hello")
public interface Hello extends java.rmi.Remote
{
    String sayHello() throws RemoteException;
}
