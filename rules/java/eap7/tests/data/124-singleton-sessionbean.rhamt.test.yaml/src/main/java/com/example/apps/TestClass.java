package com.example.apps;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.ejb.Singleton;
import java.rmi.RemoteException;

@Singleton
public class TestClass implements SessionBean {
    @Override
    public void setSessionContext(SessionContext sessionContext) throws EJBException, RemoteException {

    }

    @Override
    public void ejbRemove() throws EJBException, RemoteException {

    }

    @Override
    public void ejbActivate() throws EJBException, RemoteException {

    }

    @Override
    public void ejbPassivate() throws EJBException, RemoteException {

    }
}
