package com.example;

// Rule: messaging-change-00010
import org.springframework.boot.autoconfigure.kafka.StreamBuilderFactoryBeanCustomizer;

// Rule: messaging-change-00020
import org.springframework.boot.autoconfigure.amqp.RabbitRetryTemplateCustomizer;

public class Application {
    // Rule: messaging-change-00010
    private StreamBuilderFactoryBeanCustomizer kafkaCustomizer;

    // Rule: messaging-change-00020
    private RabbitRetryTemplateCustomizer rabbitCustomizer;

    public static void main(String[] args) {
    }
}
