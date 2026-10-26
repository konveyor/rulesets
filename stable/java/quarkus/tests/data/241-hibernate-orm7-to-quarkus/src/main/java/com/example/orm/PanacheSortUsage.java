package com.example.orm;

import io.quarkus.panache.common.Sort;

public class PanacheSortUsage {

    // Test case for hibernate-orm7-to-quarkus-00090: Panache Sort
    public Sort sortByTitle() {
        return Sort.by("title").and("id");
    }
}
