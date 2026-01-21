package com.example.apps;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.jdbc.Size;
import org.hibernate.engine.spi.Mapping;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.ForeignKeyDirection;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class Hibernate515300000_0 implements Type {

    @Override
    public boolean isAssociationType() {
        return false;
    }

    @Override
    public boolean isCollectionType() {
        return false;
    }

    @Override
    public boolean isEntityType() {
        return false;
    }

    @Override
    public boolean isAnyType() {
        return false;
    }

    @Override
    public boolean isComponentType() {
        return false;
    }

    @Override
    public int getColumnSpan(Mapping mapping) throws MappingException {
        return 0;
    }

    @Override
    public int[] sqlTypes(Mapping mapping) throws MappingException {
        return new int[0];
    }

    @Override
    public Size[] dictatedSizes(Mapping mapping) throws MappingException {
        return new Size[0];
    }

    @Override
    public Size[] defaultSizes(Mapping mapping) throws MappingException {
        return new Size[0];
    }

    @Override
    public Class getReturnedClass() {
        return null;
    }

    @Override
    public boolean isSame(Object o, Object o1) throws HibernateException {
        return false;
    }

    @Override
    public boolean isEqual(Object o, Object o1) throws HibernateException {
        return false;
    }

    @Override
    public boolean isEqual(Object o, Object o1, SessionFactoryImplementor sessionFactoryImplementor) throws HibernateException {
        return false;
    }

    @Override
    public int getHashCode(Object o) throws HibernateException {
        return 0;
    }

    @Override
    public int getHashCode(Object o, SessionFactoryImplementor sessionFactoryImplementor) throws HibernateException {
        return 0;
    }

    @Override
    public int compare(Object o, Object o1) {
        return 0;
    }

    @Override
    public boolean isDirty(Object o, Object o1, SessionImplementor sessionImplementor) throws HibernateException {
        return false;
    }

    @Override
    public boolean isDirty(Object o, Object o1, boolean[] booleans, SessionImplementor sessionImplementor) throws HibernateException {
        return false;
    }

    @Override
    public boolean isModified(Object o, Object o1, boolean[] booleans, SessionImplementor sessionImplementor) throws HibernateException {
        return false;
    }

    @Override
    public Object nullSafeGet(ResultSet resultSet, String[] strings, SessionImplementor sessionImplementor, Object o) throws HibernateException, SQLException {
        return null;
    }

    @Override
    public Object nullSafeGet(ResultSet resultSet, String s, SessionImplementor sessionImplementor, Object o) throws HibernateException, SQLException {
        return null;
    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object o, int i, boolean[] booleans, SessionImplementor sessionImplementor) throws HibernateException, SQLException {

    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object o, int i, SessionImplementor sessionImplementor) throws HibernateException, SQLException {

    }

    @Override
    public String toLoggableString(Object o, SessionFactoryImplementor sessionFactoryImplementor) throws HibernateException {
        return "";
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public Object deepCopy(Object o, SessionFactoryImplementor sessionFactoryImplementor) throws HibernateException {
        return null;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(Object o, SessionImplementor sessionImplementor, Object o1) throws HibernateException {
        return null;
    }

    @Override
    public Object assemble(Serializable serializable, SessionImplementor sessionImplementor, Object o) throws HibernateException {
        return null;
    }

    @Override
    public void beforeAssemble(Serializable serializable, SessionImplementor sessionImplementor) {

    }

    @Override
    public Object hydrate(ResultSet resultSet, String[] strings, SessionImplementor sessionImplementor, Object o) throws HibernateException, SQLException {
        return null;
    }

    @Override
    public Object resolve(Object o, SessionImplementor sessionImplementor, Object o1) throws HibernateException {
        return null;
    }

    @Override
    public Object semiResolve(Object o, SessionImplementor sessionImplementor, Object o1) throws HibernateException {
        return null;
    }

    @Override
    public Type getSemiResolvedType(SessionFactoryImplementor sessionFactoryImplementor) {
        return null;
    }

    @Override
    public Object replace(Object o, Object o1, SessionImplementor sessionImplementor, Object o2, Map map) throws HibernateException {
        return null;
    }

    @Override
    public Object replace(Object o, Object o1, SessionImplementor sessionImplementor, Object o2, Map map, ForeignKeyDirection foreignKeyDirection) throws HibernateException {
        return null;
    }

    @Override
    public boolean[] toColumnNullness(Object o, Mapping mapping) {
        return new boolean[0];
    }
}
