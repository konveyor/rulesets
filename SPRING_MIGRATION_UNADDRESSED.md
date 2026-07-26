# Spring Migration Rules - Unaddressed Issues

This document lists issues from the `spring-migration/6` label that were **not addressed** in this PR, along with the reasons.

## Issues Assigned to Other Contributors

The following issues are assigned to other contributors and were skipped to avoid duplication of effort:

| Issue | Title | Assignee | Notes |
|-------|-------|----------|-------|
| #145 | Create rules for EclipseLink | billwheatley | In progress by assignee |
| #152 | Create rules for controller detection | jmle | In progress by assignee |

## Issues Already Covered by Existing or New Rules

The following issues are already addressed by rules in this PR or existing rulesets:

| Issue | Title | Covered By |
|-------|-------|------------|
| #133 | Create rule for minimum usage of Java17 and JakartaEE 9+ | Existing baseline rules |
| #136 | Create rules for removed `org.springframework.cache.ehcache` package | removed-apis-00001 |
| #138 | Create rules for bean property determination | core-container-00001 |
| #144 | Create rules for Hibernate Validator upgrade | data-access-00060 (new) |
| #148 | Create rules for backwards compatibility with database-specific error codes | data-access-00030 |
| #151 | Create rules for the trailing slash matching configuration | web-applications-00001 |
| #154 | Create rules for RestTemplate Apache HttpClient 5 requirement | Related to #175 |
| #156 | Create rules for `SourceHttpMessageConverter` | web-applications-00030 |
| #166 | Create rules for migration to Hibernate 5.6 | data-access-00001 |

## Parent/Tracking Issues

| Issue | Title | Notes |
|-------|-------|-------|
| #161 | Support migration of Spring Framework 5->6 | Parent tracking issue - not actionable |

## Summary

- **Total issues with spring-migration/6 label:** 23
- **Issues addressed in this PR:** 11
- **Issues assigned to other contributors:** 2
- **Issues already covered by existing or new rules:** 9
- **Parent/tracking issues:** 1
