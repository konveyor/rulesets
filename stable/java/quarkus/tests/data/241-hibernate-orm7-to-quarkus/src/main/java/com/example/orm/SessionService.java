package com.example.orm;

import org.hibernate.Session;

public class SessionService {

    private final Session session;

    public SessionService(Session session) {
        this.session = session;
    }

    // Test case for hibernate-orm7-to-quarkus-00010: Session.save()
    public void create(BlogEntity entity) {
        session.save(entity);
    }

    // Test case for hibernate-orm7-to-quarkus-00020: Session.update()
    public void update(BlogEntity entity) {
        session.update(entity);
    }

    // Test case for hibernate-orm7-to-quarkus-00030: Session.delete()
    public void delete(BlogEntity entity) {
        session.delete(entity);
    }

    // Test case for hibernate-orm7-to-quarkus-00040: Session.get()
    public BlogEntity read(Long id) {
        return session.get(BlogEntity.class, id);
    }

    // Test case for hibernate-orm7-to-quarkus-00050: Session.load()
    public BlogEntity reference(Long id) {
        return session.load(BlogEntity.class, id);
    }
}
