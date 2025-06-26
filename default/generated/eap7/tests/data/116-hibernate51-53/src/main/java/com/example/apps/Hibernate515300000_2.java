package com.example.apps;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.AbstractStandardBasicType;
import org.hibernate.type.descriptor.java.JavaTypeDescriptor;
import org.hibernate.type.descriptor.sql.SqlTypeDescriptor;

public class Hibernate515300000_2 extends AbstractStandardBasicType {

    public Hibernate515300000_2(SqlTypeDescriptor sqlTypeDescriptor, JavaTypeDescriptor javaTypeDescriptor) {
        super(sqlTypeDescriptor, javaTypeDescriptor);
    }

    public void setParameterValues(Properties parameters) { }

    public void nullSafeGet(final PreparedStatement preparedStatement,
                            final Object value, final int property,
                            final SessionImplementor sessionImplementor)
            throws HibernateException, SQLException {
        preparedStatement.setNull(property, 1);
        preparedStatement.setNull(property + 1, 2);
        preparedStatement.setNull(property + 2, 2);
        preparedStatement.setNull(property + 3, 2);
    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object o, int i, boolean[] booleans, SessionImplementor sessionImplementor) throws HibernateException, SQLException {

    }

    @Override
    public String getName() {
        return "";
    }
}
