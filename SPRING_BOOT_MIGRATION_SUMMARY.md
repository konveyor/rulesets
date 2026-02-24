# Spring Boot 3.0 Migration Rules - Issues Summary

This document summarizes the issues addressed in this PR for the `spring-boot-migration/3.0` label.

## Issues Addressed (17)

This PR aims to address all issues with the `spring-boot-migration/3.0` label:

| Issue | Title | Rule File | Rule ID(s) |
|-------|-------|-----------|------------|
| #180 | Dependency review | spring-boot-2.x-to-3.0-dependencies.yaml | 00020, 00021 |
| #181 | Spring Security 6.0 migration | Spring Framework 5.x→6.0 ruleset | (see stable/java/spring-framework) |
| #183 | Configuration properties migrator | spring-boot-2.x-to-3.0-core-changes.yaml | 00040 |
| #184 | JakartaEE requirements | spring-boot-2.x-to-3.0-core-changes.yaml | 00060 |
| #185 | Core changes | spring-boot-2.x-to-3.0-core-changes.yaml | 00040-00060 |
| #186 | Web application changes | spring-boot-2.x-to-3.0-webapp-changes.yaml | 00020-00040 |
| #187 | Actuator changes | spring-boot-2.x-to-3.0-actuator.yaml (new) | 00010-00030 |
| #188 | Micrometer and metrics changes | spring-boot-2.x-to-3.0-micrometer.yaml (new) | 00010-00040 |
| #189 | Spring Security changes | Spring Framework 5.x→6.0 ruleset | (see stable/java/spring-framework) |
| #190 | Session changes | spring-boot-2.x-to-3.0-session.yaml | 00010-00020 |
| #208 | Logging date format | spring-boot-2.x-to-3.0-core-changes.yaml | 00050 |
| #215 | Jetty | spring-boot-2.x-to-3.0-webapp-changes.yaml | 00020 |
| #217 | Micrometer deprecation | spring-boot-2.x-to-3.0-micrometer.yaml | 00010, 00030 |
| #218 | Tag providers migration | spring-boot-2.x-to-3.0-micrometer.yaml | 00010 |
| #219 | JvmInfoMetrics auto-config | spring-boot-2.x-to-3.0-micrometer.yaml | 00020 |
| #227 | MySQL JDBC Driver | spring-boot-2.x-to-3.0-datasource.yaml | 00070 |
| #233 | Gradle and Maven session | spring-boot-2.x-to-3.0-session.yaml | 00010-00020 |

**Note:** Spring Security 6.0 migration rules (WebSecurityConfigurerAdapter, authorizeRequests, Lambda DSL, @Secured) live in the Spring Framework 5.x to 6.0 ruleset, not in spring-boot-2.x-to-3.0-security.yaml.

## New Rule Files Created

1. **spring-boot-2.x-to-3.0-actuator.yaml** - Actuator endpoint and health indicator changes
2. **spring-boot-2.x-to-3.0-micrometer.yaml** - Micrometer Observation API migration

## Summary of Rules Added

### Core Changes (3 new rules)
- Properties migrator recommendation
- Logging date format change
- Jakarta EE 9+ requirement

### Web Application Changes (3 new rules)
- Jetty 11+ requirement
- Path matching strategy changes
- ErrorController updates

### Security
- Existing Spring Boot 3.0 security rules (SAML2, etc.) remain; Spring Security 6.0 rules are in the Spring Framework 5.x→6.0 ruleset.

### Session (2 new rules)
- Spring Session 3.0 requirement
- Configuration property updates

### Datasource (1 new rule)
- MySQL JDBC driver coordinates change

### Dependencies (2 new rules)
- Spring Boot parent version check
- Hazelcast 5.x requirement

### Actuator (3 new rules)
- Endpoint exposure configuration
- Custom endpoint updates
- Base path configuration

### Micrometer (4 new rules)
- Tag providers to ObservationConvention
- JvmInfoMetrics auto-configuration
- Micrometer 1.10+ changes
- Direct instrumentation review
