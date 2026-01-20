package com.example.apps;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.Type;
import org.hibernate.persister.collection.CollectionPersister;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Hibernate515300001_0 {

    public void aMethod(Type type, Object owner, Serializable cached,
                        ResultSet rs, String names) throws SQLException {
        SessionImplementor session = null;
            type.assemble(cached, session, owner);
            type.beforeAssemble(cached, session);
            type.disassemble(new Object(), session, owner);
            type.hydrate(rs, new String[]{}, session, owner);
        Object oldState = null;
        Object currentState = null;
        type.isDirty(oldState, currentState, session);
        boolean[] checkable = new boolean[0];
        type.isDirty(oldState, currentState, checkable, session);
            type.isModified(oldState, currentState, checkable, session);
            type.nullSafeGet(rs, names, session, owner);
            type.nullSafeSet(null, null, 8, session);
            type.replace(null, null, session, owner, null);
            type.replace(null, null, session, owner, null, null);
            type.resolve(null, session, owner);
            type.semiResolve(null, session, owner);
            type.getHashCode(null);
            type.getHashCode(session);
        }

    public void getDeletes(CollectionPersister persister, boolean indexIsFormula) throws HibernateException {
        final Type indexType = persister.getIndexType();
        indexType.isDirty(null, null, null);
    }
}
