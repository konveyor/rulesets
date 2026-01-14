package com.example.apps;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.SessionImplementor;
//import org.hibernate.engine.spi.SessionImplementor;
//import org.hibernate.exception.JDBCExceptionHelper;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.id.PersistentIdentifierGenerator;
import org.hibernate.type.Type;

public class CustomGenerator implements PersistentIdentifierGenerator, Configurable
{

    private String tableName;

    public Serializable generate(SessionImplementor sessionImplemetor, Object object) throws HibernateException, SQLException {

        return getNextNumber(sessionImplemetor);

    }

    @Override
    public synchronized void configure(Type type, Properties params, Dialect d) throws MappingException
    {
        tableName = params.getProperty(PersistentIdentifierGenerator.TABLE);
    }

    private Long getNextNumber(SessionImplementor session) throws SQLException {
        String sql = "{call stored procedure name}";
        Long nextValue = null;
        try
        {
            PreparedStatement st = null;
            st.setString(1, tableName);
            try
            {
                ResultSet rs = st.executeQuery();
                try
                {
                    while (rs.next())
                    {
                        nextValue = Long.parseLong(rs.getString(1));
                    }
                }
                finally
                {
                    rs.close();
                }
            }
            finally
            {
            }
        }
        catch (SQLException sqle)
        {
            throw sqle;
        }
        return null;
    }

    @Override
    public String[] sqlCreateStrings(Dialect dialect) throws HibernateException {
        return new String[0];
    }

    @Override
    public String[] sqlDropStrings(Dialect dialect) throws HibernateException {
        return new String[0];
    }

    @Override
    public Object generatorKey() {
        return null;
    }

    @Override
    public Serializable generate(org.hibernate.engine.spi.SessionImplementor sessionImplementor, Object o) throws HibernateException {
        return null;
    }
}