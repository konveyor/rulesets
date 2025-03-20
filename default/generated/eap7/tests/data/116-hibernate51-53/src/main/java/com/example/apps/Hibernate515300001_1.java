package com.example.apps;

import org.hibernate.HibernateException;
import org.hibernate.annotations.CollectionType;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.collection.spi.PersistentCollection;

public class Hibernate515300001_1 {

    Object aMethod(Object collection, CollectionType collectionType)
            throws HibernateException {
        if ( collection!=null && (collection instanceof PersistentCollection) ) {
            final SessionImplementor session = null;
            PersistentCollection coll = (PersistentCollection) collection;
            if ( coll.setCurrentSession(session) ) {
//                reattachCollection( coll, collectionType );
            }
            return null;
        }
        else {
            return new Object();
        }
    }
}
