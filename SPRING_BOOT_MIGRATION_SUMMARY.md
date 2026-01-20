# Spring Boot 3.0 Migration Rules - Issues Summary

This document summarizes the issues addressed in this PR for the `spring-boot-migration/3.0` label.

## Issues Addressed (17)

All issues with the `spring-boot-migration/3.0` label have been addressed:

| Issue | Title | Rule File | Rule ID(s) |
|-------|-------|-----------|------------|
| #180 | Dependency review | dependencies.yaml | 00020, 00021 |
| #181 | Spring Security 6.0 migration | security.yaml | 00020-00050 |
| #183 | Configuration properties migrator | core-changes.yaml | 00040 |
| #184 | JakartaEE requirements | core-changes.yaml | 00060 |
| #185 | Core changes | core-changes.yaml | 00040-00060 |
| #186 | Web application changes | webapp-changes.yaml | 00020-00040 |
| #187 | Actuator changes | actuator.yaml (new) | 00010-00040 |
| #188 | Micrometer and metrics changes | micrometer.yaml (new) | 00010-00040 |
| #189 | Spring Security changes | security.yaml | 00020-00050 |
| #190 | Session changes | session.yaml | 00010-00020 |
| #208 | Logging date format | core-changes.yaml | 00050 |
| #215 | Jetty | webapp-changes.yaml | 00020 |
| #217 | Micrometer deprecation | micrometer.yaml | 00010, 00030 |
| #218 | Tag providers migration | micrometer.yaml | 00010 |
| #219 | JvmInfoMetrics auto-config | micrometer.yaml | 00020 |
| #227 | MySQL JDBC Driver | datasource.yaml | 00070 |
| #233 | Gradle and Maven session | session.yaml | 00010-00020 |

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

### Security (4 new rules)
- WebSecurityConfigurerAdapter removal
- authorizeRequests() deprecation
- Lambda DSL migration
- @Secured annotation review

### Session (2 new rules)
- Spring Session 3.0 requirement
- Configuration property updates

### Datasource (1 new rule)
- MySQL JDBC driver coordinates change

### Dependencies (2 new rules)
- Spring Boot parent version check
- Hazelcast 5.x requirement

### Actuator (4 new rules)
- Endpoint exposure configuration
- Custom endpoint updates
- Health indicator updates
- Base path configuration

### Micrometer (4 new rules)
- Tag providers to ObservationConvention
- JvmInfoMetrics auto-configuration
- Micrometer 1.10+ changes
- Direct instrumentation review
