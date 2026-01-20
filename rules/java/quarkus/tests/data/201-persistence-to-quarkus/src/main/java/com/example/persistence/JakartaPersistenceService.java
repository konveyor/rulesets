package com.example.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

// Test case for persistence-to-quarkus-00012: jakarta.persistence.PersistenceContext
public class JakartaPersistenceService {

    @PersistenceContext
    private EntityManager entityManager;

    public void updateEntity(Object entity) {
        entityManager.merge(entity);
    }
}
