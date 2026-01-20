package com.example.apps;

import org.hibernate.cfg.Configuration;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.envers.configuration.AuditConfiguration;
import org.hibernate.metamodel.source.MetadataImplementor;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;


public class MyIntegrator implements Integrator
{

//    private MyListener listener;

    public MyIntegrator()
    {
//        listener = new MyListener();
    }

    @Override
    public void integrate(org.hibernate.cfg.Configuration configuration,
                org.hibernate.engine.spi.SessionFactoryImplementor sessionFactory,
                org.hibernate.service.spi.SessionFactoryServiceRegistry serviceRegistry) 
    {
            AuditConfiguration auditConfiguration = new AuditConfiguration(null);
            Configuration configuration1 = new org.hibernate.cfg.Configuration();
            configuration1.registerTypeOverride(null);
        
        final EventListenerRegistry eventRegistry =
            serviceRegistry.getService(EventListenerRegistry.class);
    
        eventRegistry.prependListeners(EventType.POST_COMMIT_INSERT, new PostInsertEventListener() {
            @Override
            public void onPostInsert(PostInsertEvent postInsertEvent) {

            }
        });
        
//        final AuditConfiguration enversConfiguration = AuditConfiguration.getFor( configuration );
//        AuditStrategy auditStrategy = enversConfiguration.getAuditStrategy();
//
//        if (enversConfiguration.getEntCfg().hasAuditedEntities()) {
//            listenerRegistry.appendListeners( EventType.POST_DELETE, new EnversPostDeleteEventListenerImpl( enversConfiguration ) );
//            listenerRegistry.appendListeners( EventType.POST_INSERT, new EnversPostInsertEventListenerImpl( enversConfiguration ) );
//            listenerRegistry.appendListeners( EventType.POST_UPDATE, new EnversPostUpdateEventListenerImpl( enversConfiguration ) );
//            listenerRegistry.appendListeners( EventType.POST_COLLECTION_RECREATE, new EnversPostCollectionRecreateEventListenerImpl( enversConfiguration ) );
//            listenerRegistry.appendListeners( EventType.PRE_COLLECTION_REMOVE, new EnversPreCollectionRemoveEventListenerImpl( enversConfiguration ) );
//            listenerRegistry.appendListeners( EventType.PRE_COLLECTION_UPDATE, new EnversPreCollectionUpdateEventListenerImpl( enversConfiguration ) );
//        }
        
    }

    @Override
    public void integrate(MetadataImplementor metadataImplementor, SessionFactoryImplementor sessionFactoryImplementor, SessionFactoryServiceRegistry sessionFactoryServiceRegistry) {

    }

    @Override
    public void disintegrate(SessionFactoryImplementor sessionFactoryImplementor, SessionFactoryServiceRegistry sessionFactoryServiceRegistry) {

    }

}