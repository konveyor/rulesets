package com.example.persistence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EntityService {

    @PersistenceContext
    private EntityManager entityManager;

    // Test case for transaction-to-quarkus-00001: persist without @Transactional
    public void saveEntity(MyEntity entity) {
        entityManager.persist(entity);
    }

    // Test case for transaction-to-quarkus-00002: merge without @Transactional
    public void updateEntity(MyEntity entity) {
        entityManager.merge(entity);
    }

    // Test case for transaction-to-quarkus-00003: remove without @Transactional
    public void deleteEntity(MyEntity entity) {
        entityManager.remove(entity);
    }

    public MyEntity findEntity(Long id) {
        return entityManager.find(MyEntity.class, id);
    }
}
