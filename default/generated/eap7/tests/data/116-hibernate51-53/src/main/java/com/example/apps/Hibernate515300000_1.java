package com.example.apps;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.Type;
import org.hibernate.usertype.CompositeUserType;
import org.hibernate.usertype.ParameterizedType;

public class Hibernate515300000_1 implements CompositeUserType, ParameterizedType {

    @Override
    public void setParameterValues(Properties parameters) { }

    @Override
    public String[] getPropertyNames() {
        return new String[0];
    }

    @Override
    public Type[] getPropertyTypes() {
        return new Type[0];
    }

    @Override
    public Object getPropertyValue(Object o, int i) throws HibernateException {
        return null;
    }

    @Override
    public void setPropertyValue(Object o, int i, Object o1) throws HibernateException {

    }

    @Override
    public Class returnedClass() {
        return null;
    }

    @Override
    public boolean equals(Object o, Object o1) throws HibernateException {
        return false;
    }

    @Override
    public int hashCode(Object o) throws HibernateException {
        return 0;
    }

    @Override
    public Object nullSafeGet(ResultSet resultSet, String[] strings, SessionImplementor sessionImplementor, Object o) throws HibernateException, SQLException {
        return null;
    }

    @Override
    public void nullSafeSet(final PreparedStatement preparedStatement,
                            final Object value, final int property,
                            final SessionImplementor sessionImplementor)
            throws HibernateException, SQLException {
        preparedStatement.setNull(property, 1);
        preparedStatement.setNull(property + 1, 2);
        preparedStatement.setNull(property + 2, 2);
        preparedStatement.setNull(property + 3, 2);
    }

    @Override
    public Object deepCopy(Object o) throws HibernateException {
        return null;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(Object o, SessionImplementor sessionImplementor) throws HibernateException {
        return null;
    }

    @Override
    public Object assemble(Serializable serializable, SessionImplementor sessionImplementor, Object o) throws HibernateException {
        return null;
    }

    @Override
    public Object replace(Object o, Object o1, SessionImplementor sessionImplementor, Object o2) throws HibernateException {
        return null;
    }
}
