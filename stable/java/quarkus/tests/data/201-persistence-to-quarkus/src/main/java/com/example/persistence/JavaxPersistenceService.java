package com.example.persistence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

// Test case for persistence-to-quarkus-00010: javax.persistence.PersistenceContext
public class JavaxPersistenceService {

    @PersistenceContext
    private EntityManager entityManager;

    public void saveEntity(Object entity) {
        entityManager.persist(entity);
    }
}
