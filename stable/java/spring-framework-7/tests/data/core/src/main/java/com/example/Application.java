package com.example;

// Rule: core-import-00010
import javax.annotation.Resource;
import javax.annotation.PostConstruct;

// Rule: core-import-00020
import javax.inject.Inject;
import javax.inject.Named;

// Rule: core-import-00030
import org.springframework.util.concurrent.ListenableFuture;

// Rule: core-import-00040
import org.springframework.expression.spel.standard.SpelExpressionParser;

// Rule: core-import-00050
import org.springframework.expression.spel.support.SimpleEvaluationContext;

import org.springframework.core.MethodParameter;
import java.lang.reflect.Method;

public class Application {
    @Resource
    private String dataSource;

    @Inject
    @Named("myBean")
    private String myBean;

    public static void main(String[] args) throws Exception {
        Application app = new Application();
        app.init();
        app.testMethodParameter();
    }

    @PostConstruct
    public void init() {
        System.out.println("Initialized");
    }

    public ListenableFuture<String> processAsync() {
        return null;
    }

    public void parseExpression() {
        SpelExpressionParser parser = new SpelExpressionParser();
        SimpleEvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().build();
    }

    // Rule: core-method-00010
    public void testMethodParameter() throws Exception {
        Method method = Application.class.getMethod("init");
        MethodParameter param = new MethodParameter(method, -1);
        boolean optional = param.isOptional();
    }
}
