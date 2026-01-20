# Spring Migration Rules - Unaddressed Issues

This document lists issues from the `spring-migration/6` label that were **not addressed** in this PR, along with the reasons.

## Already Assigned to Other Contributors

The following issues are already assigned to other contributors and were skipped to avoid duplication of effort:

| Issue | Title | Assignee | Notes |
|-------|-------|----------|-------|
| #133 | Create rule for minimum usage of Java17 and JakartaEE 9+ | jmle | Already covered by existing baseline rules |
| #136 | Create rules for removed `org.springframework.cache.ehcache` package | jmle | Already covered by removed-apis-00001 |
| #138 | Create rules for bean property determination | jmle | Already covered by core-container-00001 |
| #144 | Create rules for Hibernate Validator upgrade | jmle | Covered by new data-access-00060 rule |
| #145 | Create rules for EclipseLink | billwheatley | In progress by assignee |
| #148 | Create rules for backwards compatibility with database-specific error codes | jayfray12 | Already covered by data-access-00030 |
| #151 | Create rules for the trailing slash matching configuration | jmle | Already covered by web-applications-00001 |
| #152 | Create rules for controller detection | jmle | In progress by assignee |
| #154 | Create rules for RestTemplate Apache HttpClient 5 requirement | jmle | Related to #175 which we addressed |
| #156 | Create rules for `SourceHttpMessageConverter` | rromannissen | Already covered by web-applications-00030 |
| #166 | Create rules for migration to Hibernate 5.6 | jayfray12 | Already covered by data-access-00001 |

## Parent/Tracking Issues

| Issue | Title | Notes |
|-------|-------|-------|
| #161 | Support migration of Spring Framework 5->6 | Parent tracking issue - not actionable |

## Summary

- **Total issues with spring-migration/6 label:** 23
- **Issues addressed in this PR:** 11
- **Issues already assigned/covered:** 11
- **Parent/tracking issues:** 1
