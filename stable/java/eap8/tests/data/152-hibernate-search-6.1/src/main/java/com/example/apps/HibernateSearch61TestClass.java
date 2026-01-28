package com.example.apps;

import org.hibernate.search.backend.elasticsearch.client.spi.ElasticsearchHttpClientConfigurationContext;
import org.hibernate.search.backend.elasticsearch.client.spi.ElasticsearchHttpClientConfigurer;
import org.hibernate.search.backend.elasticsearch.util.spi.URLEncodedString;
import org.hibernate.search.engine.backend.common.spi.FieldPaths;
import org.hibernate.search.engine.backend.document.model.dsl.spi.IndexSchemaObjectFieldNodeBuilder;
import org.hibernate.search.engine.backend.document.model.dsl.spi.IndexSchemaObjectNodeBuilder;
import org.hibernate.search.engine.backend.document.model.dsl.spi.IndexSchemaRootNodeBuilder;
import org.hibernate.search.engine.backend.index.spi.IndexManagerImplementor;
import org.hibernate.search.engine.backend.types.converter.runtime.FromDocumentFieldValueConvertContext;
import org.hibernate.search.engine.backend.types.converter.runtime.ToDocumentFieldValueConvertContext;
import org.hibernate.search.engine.backend.work.execution.spi.IndexIndexingPlan;
import org.hibernate.search.engine.backend.work.execution.spi.IndexIndexingPlanExecutionReport;
import org.hibernate.search.engine.common.timing.spi.Deadline;
import org.hibernate.search.engine.cfg.spi.ConfigurationPropertySource;
import org.hibernate.search.mapper.orm.automaticindexing.AutomaticIndexingStrategyName;
import org.hibernate.search.mapper.orm.cfg.HibernateOrmMapperSettings;
import org.hibernate.search.mapper.orm.massindexing.MassIndexingFailureHandler;
import org.hibernate.search.mapper.orm.massindexing.MassIndexingMonitor;

public class HibernateSearch61TestClass implements org.hibernate.search.engine.backend.types.converter.FromDocumentFieldValueConverter, org.hibernate.search.engine.backend.types.converter.ToDocumentFieldValueConverter {

    public static void main(String[] args) {
        ConfigurationPropertySource configurationPropertySource;
        ElasticsearchHttpClientConfigurer elasticsearchHttpClientConfigurer;
        ElasticsearchHttpClientConfigurationContext elasticsearchHttpClientConfigurationContext;
        Deadline deadline;
        IndexIndexingPlanExecutionReport indexIndexingPlanExecutionReport;
        URLEncodedString.fromJSon("{hey:1}");
        URLEncodedString.fromJsonString("{hey:1}");
        FieldPaths.absolutize("1", "2", "3");
        IndexManagerImplementor indexManagerImplementor = new IndexManagerImplementorImpl();
        indexManagerImplementor.createIndexingPlan(null, null, null, null);
        MassIndexingFailureHandler handler;
        org.hibernate.search.mapper.orm.massindexing.MassIndexingMonitor monitor;
        AutomaticIndexingStrategyName automaticIndexingStrategyName = AutomaticIndexingStrategyName.of("Hey");
        MassIndexingMonitor massIndexingMonitor = new MassIndexingMonitor() {
            @Override
            public void documentsAdded(long increment) {

            }

            @Override
            public void documentsBuilt(long increment) {

            }

            @Override
            public void entitiesLoaded(long increment) {

            }

            @Override
            public void addToTotalCount(long increment) {

            }

            @Override
            public void indexingCompleted() {

            }
        };

        org.hibernate.search.mapper.orm.automaticindexing.AutomaticIndexingStrategyName strategyName;
        String strat = org.hibernate.search.mapper.orm.cfg.HibernateOrmMapperSettings.AUTOMATIC_INDEXING_STRATEGY;
        String strat2 = org.hibernate.search.mapper.orm.cfg.HibernateOrmMapperSettings.Radicals.AUTOMATIC_INDEXING_STRATEGY;
        String strat3 = org.hibernate.search.mapper.orm.cfg.HibernateOrmMapperSettings.AutomaticIndexingRadicals.STRATEGY;
        AutomaticIndexingStrategyName strat4 = HibernateOrmMapperSettings.Defaults.AUTOMATIC_INDEXING_STRATEGY;

        IndexIndexingPlan indexIndexingPlan = null;
        indexIndexingPlan.executeAndReport();

        IndexSchemaObjectNodeBuilder indexSchemaObjectNodeBuilder;
        IndexSchemaObjectFieldNodeBuilder indexSchemaObjectFieldNodeBuilder;
        IndexSchemaRootNodeBuilder indexSchemaRootNodeBuilder;

    }

    @Override
    public Object convert(Object o, FromDocumentFieldValueConvertContext fromDocumentFieldValueConvertContext) {
        return null;
    }

    @Override
    public Object convert(Object o, ToDocumentFieldValueConvertContext toDocumentFieldValueConvertContext) {
        return null;
    }
}
