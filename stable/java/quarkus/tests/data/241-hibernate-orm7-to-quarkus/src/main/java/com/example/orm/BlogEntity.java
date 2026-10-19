package com.example.orm;

import java.util.List;

import org.hibernate.annotations.OrderBy;
import org.hibernate.annotations.Where;

// Test case for hibernate-orm7-to-quarkus-00060: @Where
@Where(clause = "deleted = false")
public class BlogEntity {

    private Long id;

    private String title;

    // Test case for hibernate-orm7-to-quarkus-00070: Hibernate @OrderBy
    @OrderBy(clause = "created_at desc")
    private List<String> comments;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getComments() {
        return comments;
    }
}
