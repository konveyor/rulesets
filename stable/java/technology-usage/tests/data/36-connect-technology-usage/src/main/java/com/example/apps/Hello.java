package com.example.apps;

import java.rmi.Remote;
import java.rmi.RemoteException;
import javax.ws.rs.Path;

@Path("")
public interface Hello extends java.rmi.Remote
{
    String sayHello() throws RemoteException;
}
