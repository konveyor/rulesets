package org.konveyor.inject;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Test class demonstrating usage of javax.inject annotations.
 * These need to be migrated to jakarta.inject in Spring 6.
 */
@Named
public class JavaxInjectService {

    @Inject
    private JavaxInjectRepository repository;

    @Inject
    public JavaxInjectService(JavaxInjectRepository repository) {
        this.repository = repository;
    }

    public String getData() {
        return repository.findData();
    }
}

interface JavaxInjectRepository {
    String findData();
}
