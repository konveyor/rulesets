package com.example.apps;

import jakarta.el.ELResolver;
import jakarta.el.ExpressionFactory;
import jakarta.enterprise.context.spi.Context;
import jakarta.enterprise.context.spi.Contextual;
import jakarta.enterprise.context.spi.CreationalContext;
import jakarta.enterprise.event.Event;
import jakarta.enterprise.inject.Instance;
import jakarta.enterprise.inject.spi.AnnotatedField;
import jakarta.enterprise.inject.spi.AnnotatedMember;
import jakarta.enterprise.inject.spi.AnnotatedMethod;
import jakarta.enterprise.inject.spi.AnnotatedParameter;
import jakarta.enterprise.inject.spi.AnnotatedType;
import jakarta.enterprise.inject.spi.Bean;
import jakarta.enterprise.inject.spi.BeanAttributes;
import jakarta.enterprise.inject.spi.BeanManager;
import jakarta.enterprise.inject.spi.Decorator;
import jakarta.enterprise.inject.spi.Extension;
import jakarta.enterprise.inject.spi.InjectionPoint;
import jakarta.enterprise.inject.spi.InjectionTarget;
import jakarta.enterprise.inject.spi.InjectionTargetFactory;
import jakarta.enterprise.inject.spi.InterceptionFactory;
import jakarta.enterprise.inject.spi.InterceptionType;
import jakarta.enterprise.inject.spi.Interceptor;
import jakarta.enterprise.inject.spi.ObserverMethod;
import jakarta.enterprise.inject.spi.ProducerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JakartaBeanManager implements BeanManager {
    @Override
    public Object getReference(Bean<?> bean, Type type, CreationalContext<?> creationalContext) {
        return null;
    }

    @Override
    public Object getInjectableReference(InjectionPoint injectionPoint, CreationalContext<?> creationalContext) {
        return null;
    }

    @Override
    public <T> CreationalContext<T> createCreationalContext(Contextual<T> contextual) {
        return null;
    }

    @Override
    public Set<Bean<?>> getBeans(Type type, Annotation... annotations) {
        return new HashSet<>();
    }

    @Override
    public Set<Bean<?>> getBeans(String s) {
        return new HashSet<>();
    }

    @Override
    public Bean<?> getPassivationCapableBean(String s) {
        return null;
    }

    @Override
    public <X> Bean<? extends X> resolve(Set<Bean<? extends X>> set) {
        return null;
    }

    @Override
    public void validate(InjectionPoint injectionPoint) {

    }

    @Override
    public void fireEvent(Object o, Annotation... annotations) {

    }

    @Override
    public <T> Set<ObserverMethod<? super T>> resolveObserverMethods(T t, Annotation... annotations) {
        return new HashSet<>();
    }

    @Override
    public List<Decorator<?>> resolveDecorators(Set<Type> set, Annotation... annotations) {
        return new ArrayList<>();
    }

    @Override
    public List<Interceptor<?>> resolveInterceptors(InterceptionType interceptionType, Annotation... annotations) {
        return new ArrayList<>();
    }

    @Override
    public boolean isScope(Class<? extends Annotation> aClass) {
        return false;
    }

    @Override
    public boolean isNormalScope(Class<? extends Annotation> aClass) {
        return false;
    }

    @Override
    public boolean isPassivatingScope(Class<? extends Annotation> aClass) {
        return false;
    }

    @Override
    public boolean isQualifier(Class<? extends Annotation> aClass) {
        return false;
    }

    @Override
    public boolean isInterceptorBinding(Class<? extends Annotation> aClass) {
        return false;
    }

    @Override
    public boolean isStereotype(Class<? extends Annotation> aClass) {
        return false;
    }

    @Override
    public Set<Annotation> getInterceptorBindingDefinition(Class<? extends Annotation> aClass) {
        return new HashSet<>();
    }

    @Override
    public Set<Annotation> getStereotypeDefinition(Class<? extends Annotation> aClass) {
        return new HashSet<>();
    }

    @Override
    public boolean areQualifiersEquivalent(Annotation annotation, Annotation annotation1) {
        return false;
    }

    @Override
    public boolean areInterceptorBindingsEquivalent(Annotation annotation, Annotation annotation1) {
        return false;
    }

    @Override
    public int getQualifierHashCode(Annotation annotation) {
        return 0;
    }

    @Override
    public int getInterceptorBindingHashCode(Annotation annotation) {
        return 0;
    }

    @Override
    public Context getContext(Class<? extends Annotation> aClass) {
        return null;
    }

    @Override
    public ELResolver getELResolver() {
        return null;
    }

    @Override
    public ExpressionFactory wrapExpressionFactory(ExpressionFactory expressionFactory) {
        return null;
    }

    @Override
    public <T> AnnotatedType<T> createAnnotatedType(Class<T> aClass) {
        return null;
    }

    @Override
    public <T> InjectionTarget<T> createInjectionTarget(AnnotatedType<T> annotatedType) {
        return null;
    }

    @Override
    public <T> InjectionTargetFactory<T> getInjectionTargetFactory(AnnotatedType<T> annotatedType) {
        return null;
    }

    @Override
    public <X> ProducerFactory<X> getProducerFactory(AnnotatedField<? super X> annotatedField, Bean<X> bean) {
        return null;
    }

    @Override
    public <X> ProducerFactory<X> getProducerFactory(AnnotatedMethod<? super X> annotatedMethod, Bean<X> bean) {
        return null;
    }

    @Override
    public <T> BeanAttributes<T> createBeanAttributes(AnnotatedType<T> annotatedType) {
        return null;
    }

    @Override
    public BeanAttributes<?> createBeanAttributes(AnnotatedMember<?> annotatedMember) {
        return null;
    }

    @Override
    public <T> Bean<T> createBean(BeanAttributes<T> beanAttributes, Class<T> aClass, InjectionTargetFactory<T> injectionTargetFactory) {
        return null;
    }

    @Override
    public <T, X> Bean<T> createBean(BeanAttributes<T> beanAttributes, Class<X> aClass, ProducerFactory<X> producerFactory) {
        return null;
    }

    @Override
    public InjectionPoint createInjectionPoint(AnnotatedField<?> annotatedField) {
        return null;
    }

    @Override
    public InjectionPoint createInjectionPoint(AnnotatedParameter<?> annotatedParameter) {
        return null;
    }

    @Override
    public <T extends Extension> T getExtension(Class<T> aClass) {
        return null;
    }

    @Override
    public <T> InterceptionFactory<T> createInterceptionFactory(CreationalContext<T> creationalContext, Class<T> aClass) {
        return null;
    }

    @Override
    public Event<Object> getEvent() {
        return null;
    }

    @Override
    public Instance<Object> createInstance() {
        return null;
    }
}
