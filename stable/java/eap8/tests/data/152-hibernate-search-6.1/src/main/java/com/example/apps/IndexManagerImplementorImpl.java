package com.example.apps;

import org.hibernate.search.engine.backend.common.spi.EntityReferenceFactory;
import org.hibernate.search.engine.backend.index.IndexManager;
import org.hibernate.search.engine.backend.index.spi.IndexManagerImplementor;
import org.hibernate.search.engine.backend.index.spi.IndexManagerStartContext;
import org.hibernate.search.engine.backend.mapping.spi.BackendMappingContext;
import org.hibernate.search.engine.backend.schema.management.spi.IndexSchemaManager;
import org.hibernate.search.engine.backend.scope.spi.IndexScopeBuilder;
import org.hibernate.search.engine.backend.session.spi.BackendSessionContext;
import org.hibernate.search.engine.backend.session.spi.DetachedBackendSessionContext;
import org.hibernate.search.engine.backend.work.execution.DocumentCommitStrategy;
import org.hibernate.search.engine.backend.work.execution.DocumentRefreshStrategy;
import org.hibernate.search.engine.backend.work.execution.spi.IndexIndexer;
import org.hibernate.search.engine.backend.work.execution.spi.IndexIndexingPlan;
import org.hibernate.search.engine.backend.work.execution.spi.IndexWorkspace;

import java.util.concurrent.CompletableFuture;

public class IndexManagerImplementorImpl implements IndexManagerImplementor {
    @Override
    public void start(IndexManagerStartContext indexManagerStartContext) {

    }

    @Override
    public CompletableFuture<?> preStop() {
        return null;
    }

    @Override
    public void stop() {

    }

    @Override
    public IndexManager toAPI() {
        return null;
    }

    @Override
    public IndexSchemaManager schemaManager() {
        return null;
    }

    @Override
    public <R> IndexIndexingPlan<R> createIndexingPlan(BackendSessionContext backendSessionContext, EntityReferenceFactory<R> entityReferenceFactory, DocumentCommitStrategy documentCommitStrategy, DocumentRefreshStrategy documentRefreshStrategy) {
        return null;
    }

    @Override
    public IndexIndexer createIndexer(BackendSessionContext backendSessionContext) {
        return null;
    }

    @Override
    public IndexWorkspace createWorkspace(DetachedBackendSessionContext detachedBackendSessionContext) {
        return null;
    }

    @Override
    public IndexScopeBuilder createScopeBuilder(BackendMappingContext backendMappingContext) {
        return null;
    }

    @Override
    public void addTo(IndexScopeBuilder indexScopeBuilder) {

    }
}
