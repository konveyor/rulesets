package com.example.apps;

import org.hibernate.Session;
import org.hibernate.search.backend.impl.jms.AbstractJMSHibernateSearchController;


public class JMSController extends AbstractJMSHibernateSearchController {

    @Override
    protected Session getSession() {
        return null;
    }

    @Override
    protected void cleanSessionIfNeeded(Session session) {

    }
}