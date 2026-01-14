package com.example.apps;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.MappingException;
import net.sf.hibernate.Session;
import net.sf.hibernate.SessionFactory;
import net.sf.hibernate.Transaction;
import net.sf.hibernate.cfg.Configuration;
import org.hibernate.CacheMode;
import org.hibernate.EntityMode;
import org.hibernate.FlushMode;
import org.hibernate.Interceptor;
import org.hibernate.Query;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.collection.PersistentCollection;
//import org.hibernate.connection.C3P0ConnectionProvider;
import org.hibernate.dialect.resolver.DialectResolver;
import org.hibernate.engine.EntityKey;
import org.hibernate.engine.LoadQueryInfluencers;
import org.hibernate.engine.NonFlushedChanges;
import org.hibernate.engine.PersistenceContext;
import org.hibernate.engine.QueryParameters;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.engine.query.sql.NativeSQLQuerySpecification;
import org.hibernate.event.EventListeners;
import org.hibernate.impl.CriteriaImpl;
import org.hibernate.jdbc.Batcher;
import org.hibernate.jdbc.JDBCContext;
import org.hibernate.jdbc.NonBatchingBatcherFactory;
import org.hibernate.jdbc.NonBatchingBatcher;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.collection.PersistentBag;
import org.hibernate.loader.custom.CustomQuery;
import org.hibernate.persister.entity.EntityPersister;
import org.hibernate.type.Type;

public class Example
{

    private static SessionFactory _sessions = null;
    private static Properties pops = new Properties();
    
    private PersistentBag resultBag;

    static
    {
        try
        {

            InputStream stream = Thread.currentThread().getContextClassLoader().getResource("hibernate.properties").openStream();// Example.class.getResourceAsStream("hibernate.properties");
            try
            {
                pops.load(stream);
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
            }
            Configuration cfg = new Configuration();
            cfg.addClass(Person.class);
            cfg.setProperties(pops);
            _sessions = cfg.buildSessionFactory();
            if (_sessions == null)
            {
                System.out.println("This _session is nULLL");
            }
        }
        catch (MappingException e)
        {
            e.printStackTrace();
        }
        catch (HibernateException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws HibernateException
    {

        Person person = new Person();
        person.setName("John Smith");
        person.setEmail("nobody@domain.com");

        Session session = _sessions.openSession();

        Transaction tx = null;
        try
        {
            tx = session.beginTransaction();
            session.save(person);
            tx.commit();
        }
        catch (HibernateException he)
        {
            if (tx != null)
                tx.rollback();
            throw he;
        }
        finally
        {
            session.close();
        }

    }

    private class MySessionImplementor implements SessionImplementor
    {

        @Override
        public Interceptor getInterceptor() {
            return null;
        }

        @Override
        public void setAutoClear(boolean b) {

        }

        @Override
        public boolean isTransactionInProgress() {
            return false;
        }

        @Override
        public void initializeCollection(PersistentCollection persistentCollection, boolean b) throws org.hibernate.HibernateException {

        }

        @Override
        public Object internalLoad(String s, Serializable serializable, boolean b, boolean b1) throws org.hibernate.HibernateException {
            return null;
        }

        @Override
        public Object immediateLoad(String s, Serializable serializable) throws org.hibernate.HibernateException {
            return null;
        }

        @Override
        public long getTimestamp() {
            return 0;
        }

        @Override
        public SessionFactoryImplementor getFactory() {
            return null;
        }

        @Override
        public Batcher getBatcher() {
            return null;
        }

        @Override
        public List list(String s, QueryParameters queryParameters) throws org.hibernate.HibernateException {
            return new ArrayList();
        }

        @Override
        public Iterator iterate(String s, QueryParameters queryParameters) throws org.hibernate.HibernateException {
            return null;
        }

        @Override
        public ScrollableResults scroll(String s, QueryParameters queryParameters) throws org.hibernate.HibernateException {
            return null;
        }

        @Override
        public ScrollableResults scroll(CriteriaImpl criteria, ScrollMode scrollMode) {
            return null;
        }

        @Override
        public List list(CriteriaImpl criteria) {
            return new ArrayList();
        }

        @Override
        public List listFilter(Object o, String s, QueryParameters queryParameters) throws org.hibernate.HibernateException {
            return new ArrayList();
        }

        @Override
        public Iterator iterateFilter(Object o, String s, QueryParameters queryParameters) throws org.hibernate.HibernateException {
            return null;
        }

        @Override
        public EntityPersister getEntityPersister(String s, Object o) throws org.hibernate.HibernateException {
            return null;
        }

        @Override
        public Object getEntityUsingInterceptor(EntityKey entityKey) throws org.hibernate.HibernateException {
            return null;
        }

        @Override
        public void afterTransactionCompletion(boolean b, org.hibernate.Transaction transaction) {

        }

        @Override
        public void beforeTransactionCompletion(org.hibernate.Transaction transaction) {

        }

        @Override
        public Serializable getContextEntityIdentifier(Object o) {
            return null;
        }

        @Override
        public String bestGuessEntityName(Object o) {
            return "";
        }

        @Override
        public String guessEntityName(Object o) throws org.hibernate.HibernateException {
            return "";
        }

        @Override
        public Object instantiate(String s, Serializable serializable) throws org.hibernate.HibernateException {
            return null;
        }

        @Override
        public List listCustomQuery(CustomQuery customQuery, QueryParameters queryParameters) throws org.hibernate.HibernateException {
            return new ArrayList();
        }

        @Override
        public ScrollableResults scrollCustomQuery(CustomQuery customQuery, QueryParameters queryParameters) throws org.hibernate.HibernateException {
            return null;
        }

        @Override
        public List list(NativeSQLQuerySpecification nativeSQLQuerySpecification, QueryParameters queryParameters) throws org.hibernate.HibernateException {
            return new ArrayList();
        }

        @Override
        public ScrollableResults scroll(NativeSQLQuerySpecification nativeSQLQuerySpecification, QueryParameters queryParameters) throws org.hibernate.HibernateException {
            return null;
        }

        @Override
        public Object getFilterParameterValue(String s) {
            return null;
        }

        @Override
        public Type getFilterParameterType(String s) {
            return null;
        }

        @Override
        public Map getEnabledFilters() {
            return new HashMap();
        }

        @Override
        public int getDontFlushFromFind() {
            return 0;
        }

        @Override
        public EventListeners getListeners() {
            return null;
        }

        @Override
        public PersistenceContext getPersistenceContext() {
            return null;
        }

        @Override
        public int executeUpdate(String s, QueryParameters queryParameters) throws org.hibernate.HibernateException {
            return 0;
        }

        @Override
        public int executeNativeUpdate(NativeSQLQuerySpecification nativeSQLQuerySpecification, QueryParameters queryParameters) throws org.hibernate.HibernateException {
            return 0;
        }

        @Override
        public NonFlushedChanges getNonFlushedChanges() throws org.hibernate.HibernateException {
            return null;
        }

        @Override
        public void applyNonFlushedChanges(NonFlushedChanges nonFlushedChanges) throws org.hibernate.HibernateException {

        }

        @Override
        public EntityMode getEntityMode() {
            return null;
        }

        @Override
        public CacheMode getCacheMode() {
            return null;
        }

        @Override
        public void setCacheMode(CacheMode cacheMode) {

        }

        @Override
        public boolean isOpen() {
            return false;
        }

        @Override
        public boolean isConnected() {
            return false;
        }

        @Override
        public FlushMode getFlushMode() {
            return null;
        }

        @Override
        public void setFlushMode(FlushMode flushMode) {

        }

        @Override
        public Connection connection() {
            return null;
        }

        @Override
        public void flush() {

        }

        @Override
        public Query getNamedQuery(String s) {
            return null;
        }

        @Override
        public Query getNamedSQLQuery(String s) {
            return null;
        }

        @Override
        public boolean isEventSource() {
            return false;
        }

        @Override
        public void afterScrollOperation() {

        }

        @Override
        public String getFetchProfile() {
            return "";
        }

        @Override
        public void setFetchProfile(String s) {

        }

        @Override
        public JDBCContext getJDBCContext() {
            return null;
        }

        @Override
        public boolean isClosed() {
            return false;
        }

        @Override
        public LoadQueryInfluencers getLoadQueryInfluencers() {
            return null;
        }
    }
}
