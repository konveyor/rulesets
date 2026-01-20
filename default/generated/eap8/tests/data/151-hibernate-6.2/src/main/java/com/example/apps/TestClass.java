package com.example.apps;

import org.hibernate.EntityMode;
import org.hibernate.HibernateException;
import org.hibernate.LockOptions;
import org.hibernate.MappingException;
import org.hibernate.action.spi.AfterTransactionCompletionProcess;
import org.hibernate.action.spi.BeforeTransactionCompletionProcess;
import org.hibernate.bytecode.spi.BytecodeEnhancementMetadata;
import org.hibernate.cache.spi.access.EntityDataAccess;
import org.hibernate.cache.spi.access.NaturalIdDataAccess;
import org.hibernate.cache.spi.entry.CacheEntry;
import org.hibernate.cache.spi.entry.CacheEntryStructure;
import org.hibernate.engine.spi.CascadeStyle;
import org.hibernate.engine.spi.EntityEntryFactory;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.engine.spi.ValueInclusion;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.internal.FilterAliasGenerator;
import org.hibernate.internal.SessionCreationOptions;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.internal.SessionImpl;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.metamodel.model.domain.NavigableRole;
import org.hibernate.persister.entity.EntityPersister;
import org.hibernate.LockMode;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.action.spi.Executable;

import java.io.Serializable;
import java.lang.Object;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.hibernate.persister.entity.MultiLoadOptions;
import org.hibernate.persister.walking.spi.AttributeDefinition;
import org.hibernate.persister.walking.spi.EntityIdentifierDefinition;
import org.hibernate.tuple.entity.EntityMetamodel;
import org.hibernate.tuple.entity.EntityTuplizer;
import org.hibernate.type.Type;
import org.hibernate.type.VersionType;
import org.hibernate.type.descriptor.jdbc.JdbcType;
import org.hibernate.cfg.AnnotatedClassType;
import org.hibernate.cfg.annotations.ArrayBinder;
import org.hibernate.loader.AbstractEntityJoinWalker;
import org.hibernate.loader.collection.BatchingCollectionInitializer;

public class TestClass {
    public static void main(String[] args) {
        EntityPersister persister = new EntityPersisterImpl();
//        persister.lock(new Object(), new Object(), LockMode.FORCE, new SharedSessionContractImplementor());
        Object o1 = new Object();
        Object oa = new Object[]{o1, o1};
        persister.multiLoad(null, null, null);

        Executable executable = new ExecutableImpl();
        executable.afterDeserialize(new SharedSessionContractImplementor(null, null));
        JdbcType type = new JdbcType();
        type.getJdbcRecommendedJavaTypeMapping();
    }

    static class SharedSessionContractImplementor extends SessionImpl {
        public SharedSessionContractImplementor(SessionFactoryImpl factory, SessionCreationOptions options) {
            super(factory, options);
        }
    }

    static class EntityPersisterImpl implements EntityPersister {
        @Override
        public void generateEntityDefinition() {

        }

        @Override
        public void postInstantiate() throws MappingException {

        }

        @Override
        public SessionFactoryImplementor getFactory() {
            return null;
        }

        @Override
        public NavigableRole getNavigableRole() {
            return null;
        }

        @Override
        public EntityEntryFactory getEntityEntryFactory() {
            return null;
        }

        @Override
        public String getRootEntityName() {
            return "";
        }

        @Override
        public String getEntityName() {
            return "";
        }

        @Override
        public EntityMetamodel getEntityMetamodel() {
            return null;
        }

        @Override
        public boolean isSubclassEntityName(String s) {
            return false;
        }

        @Override
        public Serializable[] getPropertySpaces() {
            return new Serializable[0];
        }

        @Override
        public Serializable[] getQuerySpaces() {
            return new Serializable[0];
        }

        @Override
        public boolean hasProxy() {
            return false;
        }

        @Override
        public boolean hasCollections() {
            return false;
        }

        @Override
        public boolean hasMutableProperties() {
            return false;
        }

        @Override
        public boolean hasSubselectLoadableCollections() {
            return false;
        }

        @Override
        public boolean hasCascades() {
            return false;
        }

        @Override
        public boolean isMutable() {
            return false;
        }

        @Override
        public boolean isInherited() {
            return false;
        }

        @Override
        public boolean isIdentifierAssignedByInsert() {
            return false;
        }

        @Override
        public Type getPropertyType(String s) throws MappingException {
            return null;
        }

        @Override
        public int[] findDirty(Object[] objects, Object[] objects1, Object o, org.hibernate.engine.spi.SharedSessionContractImplementor sharedSessionContractImplementor) {
            return new int[0];
        }

        @Override
        public int[] findModified(Object[] objects, Object[] objects1, Object o, org.hibernate.engine.spi.SharedSessionContractImplementor sharedSessionContractImplementor) {
            return new int[0];
        }

        public int[] findDirty(Object[] objects, Object[] objects1, Object o, SharedSessionContractImplementor sharedSessionContractImplementor) {
            return new int[0];
        }

        public int[] findModified(Object[] objects, Object[] objects1, Object o, SharedSessionContractImplementor sharedSessionContractImplementor) {
            return new int[0];
        }

        @Override
        public boolean hasIdentifierProperty() {
            return false;
        }

        @Override
        public boolean canExtractIdOutOfEntity() {
            return false;
        }

        @Override
        public boolean isVersioned() {
            return false;
        }

        @Override
        public VersionType getVersionType() {
            return null;
        }

        @Override
        public int getVersionProperty() {
            return 0;
        }

        @Override
        public boolean hasNaturalIdentifier() {
            return false;
        }

        @Override
        public int[] getNaturalIdentifierProperties() {
            return new int[0];
        }

        @Override
        public Object[] getNaturalIdentifierSnapshot(Serializable serializable, org.hibernate.engine.spi.SharedSessionContractImplementor sharedSessionContractImplementor) {
            return new Object[0];
        }

        public Object[] getNaturalIdentifierSnapshot(Serializable serializable, SharedSessionContractImplementor sharedSessionContractImplementor) {
            return new Object[0];
        }

        @Override
        public IdentifierGenerator getIdentifierGenerator() {
            return null;
        }

        @Override
        public boolean hasLazyProperties() {
            return false;
        }

        @Override
        public Serializable loadEntityIdByNaturalId(Object[] objects, LockOptions lockOptions, org.hibernate.engine.spi.SharedSessionContractImplementor sharedSessionContractImplementor) {
            return null;
        }

        @Override
        public Object load(Serializable serializable, Object o, LockMode lockMode, org.hibernate.engine.spi.SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException {
            return null;
        }

        @Override
        public Object load(Serializable serializable, Object o, LockOptions lockOptions, org.hibernate.engine.spi.SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException {
            return null;
        }

        @Override
        public List multiLoad(Serializable[] serializables, org.hibernate.engine.spi.SharedSessionContractImplementor sharedSessionContractImplementor, MultiLoadOptions multiLoadOptions) {
            return Collections.emptyList();
        }

        @Override
        public void lock(Serializable serializable, Object o, Object o1, LockMode lockMode, org.hibernate.engine.spi.SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException {

        }

        @Override
        public void lock(Serializable serializable, Object o, Object o1, LockOptions lockOptions, org.hibernate.engine.spi.SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException {

        }

        @Override
        public void insert(Serializable serializable, Object[] objects, Object o, org.hibernate.engine.spi.SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException {

        }

        @Override
        public Serializable insert(Object[] objects, Object o, org.hibernate.engine.spi.SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException {
            return null;
        }

        @Override
        public void delete(Serializable serializable, Object o, Object o1, org.hibernate.engine.spi.SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException {

        }

        @Override
        public void update(Serializable serializable, Object[] objects, int[] ints, boolean b, Object[] objects1, Object o, Object o1, Object o2, org.hibernate.engine.spi.SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException {

        }

        public Serializable loadEntityIdByNaturalId(Object[] objects, LockOptions lockOptions, SharedSessionContractImplementor sharedSessionContractImplementor) {
            return null;
        }

        public Object load(Serializable serializable, Object o, LockMode lockMode, SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException {
            return null;
        }

        public Object load(Serializable serializable, Object o, LockOptions lockOptions, SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException {
            return null;
        }

        public List multiLoad(Serializable[] serializables, SharedSessionContractImplementor sharedSessionContractImplementor, MultiLoadOptions multiLoadOptions) {
            return new ArrayList();
        }

        public void lock(Serializable serializable, Object o, Object o1, LockMode lockMode, SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException {

        }

        public void lock(Serializable serializable, Object o, Object o1, LockOptions lockOptions, SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException {

        }

        public void insert(Serializable serializable, Object[] objects, Object o, SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException {

        }

        public Serializable insert(Object[] objects, Object o, SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException {
            return null;
        }

        public void delete(Serializable serializable, Object o, Object o1, SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException {

        }

        public void update(Serializable serializable, Object[] objects, int[] ints, boolean b, Object[] objects1, Object o, Object o1, Object o2, SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException {

        }

        @Override
        public Type[] getPropertyTypes() {
            return new Type[0];
        }

        @Override
        public String[] getPropertyNames() {
            return new String[0];
        }

        @Override
        public boolean[] getPropertyInsertability() {
            return new boolean[0];
        }

        @Override
        public ValueInclusion[] getPropertyInsertGenerationInclusions() {
            return new ValueInclusion[0];
        }

        @Override
        public ValueInclusion[] getPropertyUpdateGenerationInclusions() {
            return new ValueInclusion[0];
        }

        @Override
        public boolean[] getPropertyUpdateability() {
            return new boolean[0];
        }

        @Override
        public boolean[] getPropertyCheckability() {
            return new boolean[0];
        }

        @Override
        public boolean[] getPropertyNullability() {
            return new boolean[0];
        }

        @Override
        public boolean[] getPropertyVersionability() {
            return new boolean[0];
        }

        @Override
        public boolean[] getPropertyLaziness() {
            return new boolean[0];
        }

        @Override
        public CascadeStyle[] getPropertyCascadeStyles() {
            return new CascadeStyle[0];
        }

        @Override
        public Type getIdentifierType() {
            return null;
        }

        @Override
        public String getIdentifierPropertyName() {
            return "";
        }

        @Override
        public boolean isCacheInvalidationRequired() {
            return false;
        }

        @Override
        public boolean isLazyPropertiesCacheable() {
            return false;
        }

        @Override
        public boolean canReadFromCache() {
            return false;
        }

        @Override
        public boolean canWriteToCache() {
            return false;
        }

        @Override
        public boolean hasCache() {
            return false;
        }

        @Override
        public EntityDataAccess getCacheAccessStrategy() {
            return null;
        }

        @Override
        public CacheEntryStructure getCacheEntryStructure() {
            return null;
        }

        @Override
        public CacheEntry buildCacheEntry(Object o, Object[] objects, Object o1, org.hibernate.engine.spi.SharedSessionContractImplementor sharedSessionContractImplementor) {
            return null;
        }

        public CacheEntry buildCacheEntry(Object o, Object[] objects, Object o1, SharedSessionContractImplementor sharedSessionContractImplementor) {
            return null;
        }

        @Override
        public boolean hasNaturalIdCache() {
            return false;
        }

        @Override
        public NaturalIdDataAccess getNaturalIdCacheAccessStrategy() {
            return null;
        }

        @Override
        public ClassMetadata getClassMetadata() {
            return null;
        }

        @Override
        public boolean isBatchLoadable() {
            return false;
        }

        @Override
        public boolean isSelectBeforeUpdateRequired() {
            return false;
        }

        @Override
        public Object[] getDatabaseSnapshot(Serializable serializable, org.hibernate.engine.spi.SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException {
            return new Object[0];
        }

        @Override
        public Serializable getIdByUniqueKey(Serializable serializable, String s, org.hibernate.engine.spi.SharedSessionContractImplementor sharedSessionContractImplementor) {
            return null;
        }

        @Override
        public Object getCurrentVersion(Serializable serializable, org.hibernate.engine.spi.SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException {
            return null;
        }

        @Override
        public Object forceVersionIncrement(Serializable serializable, Object o, org.hibernate.engine.spi.SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException {
            return null;
        }

        public Object[] getDatabaseSnapshot(Serializable serializable, SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException {
            return new Object[0];
        }

        public Serializable getIdByUniqueKey(Serializable serializable, String s, SharedSessionContractImplementor sharedSessionContractImplementor) {
            return null;
        }

        public Object getCurrentVersion(Serializable serializable, SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException {
            return null;
        }

        public Object forceVersionIncrement(Serializable serializable, Object o, SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException {
            return null;
        }

        @Override
        public boolean isInstrumented() {
            return false;
        }

        @Override
        public boolean hasInsertGeneratedProperties() {
            return false;
        }

        @Override
        public boolean hasUpdateGeneratedProperties() {
            return false;
        }

        @Override
        public boolean isVersionPropertyGenerated() {
            return false;
        }

        @Override
        public void afterInitialize(Object o, org.hibernate.engine.spi.SharedSessionContractImplementor sharedSessionContractImplementor) {

        }

        @Override
        public void afterReassociate(Object o, org.hibernate.engine.spi.SharedSessionContractImplementor sharedSessionContractImplementor) {

        }

        @Override
        public Object createProxy(Serializable serializable, org.hibernate.engine.spi.SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException {
            return null;
        }

        @Override
        public Boolean isTransient(Object o, org.hibernate.engine.spi.SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException {
            return null;
        }

        @Override
        public Object[] getPropertyValuesToInsert(Object o, Map map, org.hibernate.engine.spi.SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException {
            return new Object[0];
        }

        @Override
        public void processInsertGeneratedProperties(Serializable serializable, Object o, Object[] objects, org.hibernate.engine.spi.SharedSessionContractImplementor sharedSessionContractImplementor) {

        }

        @Override
        public void processUpdateGeneratedProperties(Serializable serializable, Object o, Object[] objects, org.hibernate.engine.spi.SharedSessionContractImplementor sharedSessionContractImplementor) {

        }

        public void afterInitialize(Object o, SharedSessionContractImplementor sharedSessionContractImplementor) {

        }

        public void afterReassociate(Object o, SharedSessionContractImplementor sharedSessionContractImplementor) {

        }

        public Object createProxy(Serializable serializable, SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException {
            return null;
        }

        public Boolean isTransient(Object o, SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException {
            return null;
        }

        public Object[] getPropertyValuesToInsert(Object o, Map map, SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException {
            return new Object[0];
        }

        public void processInsertGeneratedProperties(Serializable serializable, Object o, Object[] objects, SharedSessionContractImplementor sharedSessionContractImplementor) {

        }

        public void processUpdateGeneratedProperties(Serializable serializable, Object o, Object[] objects, SharedSessionContractImplementor sharedSessionContractImplementor) {

        }

        @Override
        public Class getMappedClass() {
            return null;
        }

        @Override
        public boolean implementsLifecycle() {
            return false;
        }

        @Override
        public Class getConcreteProxyClass() {
            return null;
        }

        @Override
        public void setPropertyValues(Object o, Object[] objects) {

        }

        @Override
        public void setPropertyValue(Object o, int i, Object o1) {

        }

        @Override
        public Object[] getPropertyValues(Object o) {
            return new Object[0];
        }

        @Override
        public Object getPropertyValue(Object o, int i) throws HibernateException {
            return null;
        }

        @Override
        public Object getPropertyValue(Object o, String s) {
            return null;
        }

        @Override
        public Serializable getIdentifier(Object o) throws HibernateException {
            return null;
        }

        @Override
        public Serializable getIdentifier(Object o, org.hibernate.engine.spi.SharedSessionContractImplementor sharedSessionContractImplementor) {
            return null;
        }

        @Override
        public void setIdentifier(Object o, Serializable serializable, org.hibernate.engine.spi.SharedSessionContractImplementor sharedSessionContractImplementor) {

        }

        public Serializable getIdentifier(Object o, SharedSessionContractImplementor sharedSessionContractImplementor) {
            return null;
        }

        public void setIdentifier(Object o, Serializable serializable, SharedSessionContractImplementor sharedSessionContractImplementor) {

        }

        @Override
        public Object getVersion(Object o) throws HibernateException {
            return null;
        }

        @Override
        public Object instantiate(Serializable serializable, org.hibernate.engine.spi.SharedSessionContractImplementor sharedSessionContractImplementor) {
            return null;
        }

        public Object instantiate(Serializable serializable, SharedSessionContractImplementor sharedSessionContractImplementor) {
            return null;
        }

        @Override
        public boolean isInstance(Object o) {
            return false;
        }

        @Override
        public boolean hasUninitializedLazyProperties(Object o) {
            return false;
        }

        @Override
        public void resetIdentifier(Object o, Serializable serializable, Object o1, org.hibernate.engine.spi.SharedSessionContractImplementor sharedSessionContractImplementor) {

        }

        public void resetIdentifier(Object o, Serializable serializable, Object o1, SharedSessionContractImplementor sharedSessionContractImplementor) {

        }

        @Override
        public EntityPersister getSubclassEntityPersister(Object o, SessionFactoryImplementor sessionFactoryImplementor) {
            return null;
        }

        @Override
        public EntityMode getEntityMode() {
            return null;
        }

        @Override
        public EntityTuplizer getEntityTuplizer() {
            return null;
        }

        @Override
        public BytecodeEnhancementMetadata getInstrumentationMetadata() {
            return null;
        }

        @Override
        public FilterAliasGenerator getFilterAliasGenerator(String s) {
            return null;
        }

        @Override
        public int[] resolveAttributeIndexes(String[] strings) {
            return new int[0];
        }

        @Override
        public boolean canUseReferenceCacheEntries() {
            return false;
        }

        @Override
        public EntityPersister getEntityPersister() {
            return null;
        }

        @Override
        public EntityIdentifierDefinition getEntityKeyDefinition() {
            return null;
        }

        @Override
        public Iterable<AttributeDefinition> getAttributes() {
            return null;
        }
    }

    static class ExecutableImpl implements Executable {
        @Override
        public Serializable[] getPropertySpaces() {
            return new Serializable[0];
        }

        @Override
        public void beforeExecutions() throws HibernateException {

        }

        @Override
        public void execute() throws HibernateException {

        }

        @Override
        public AfterTransactionCompletionProcess getAfterTransactionCompletionProcess() {
            return null;
        }

        @Override
        public BeforeTransactionCompletionProcess getBeforeTransactionCompletionProcess() {
            return null;
        }

        @Override
        public void afterDeserialize(org.hibernate.engine.spi.SharedSessionContractImplementor sharedSessionContractImplementor) {

        }

        public void afterDeserialize(SharedSessionContractImplementor sharedSessionContractImplementor) {

        }
    }
}