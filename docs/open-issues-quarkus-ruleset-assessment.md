# Assessment: open `quarkus-ruleset` GitHub issues

This document summarizes [open issues labeled `quarkus-ruleset`](https://github.com/konveyor/rulesets/issues?q=state%3Aopen%20label%3Aquarkus-ruleset) on `konveyor/rulesets`, restates each request, and outlines what implementing it would likely involve in this repository.

**Repository note:** Issues often link to `default/generated/quarkus/*.windup.yaml`. In this tree, the maintained Spring-to-Quarkus rules live under [`stable/java/quarkus/`](../stable/java/quarkus/) (about 35 rule files). Any implementation work should target those paths unless the project’s packaging pipeline explicitly copies from elsewhere.

**Effort scale:** Each issue is assigned a rank **1–5** (1 = smallest effort, 5 = largest). **Medium** splits the difference between focused YAML work and multi-rule or open-ended research. **Medium–large** means substantial content or many triggers/tests, without a full ruleset restructure.

---

## Summary (sorted by effort, then issue number)

| Effort | Issue | Title (short) | Nature |
|:------:|-------|---------------|--------|
| **1** | [#289](https://github.com/konveyor/rulesets/issues/289) | Fix JMX rule messaging (native vs JMX; Micrometer) | Copy + links in existing rules |
| **3** | [#290](https://github.com/konveyor/rulesets/issues/290) | Improve Spring DI rule (category, table, CDI guidance) | YAML + possible new `when` clauses + tests |
| **3** | [#292](https://github.com/konveyor/rulesets/issues/292) | Explain `@SpringBootApplication` composition in Quarkus terms | Message + optional extra rules |
| **3** | [#294](https://github.com/konveyor/rulesets/issues/294) | Spring Cloud Consul → Quarkus Stork | New rule file + tests + dependency patterns |
| **3** | [#295](https://github.com/konveyor/rulesets/issues/295) | Container image build guidance (Jib, Buildpack, S2I, …) | New rule file(s) + tests |
| **4** | [#291](https://github.com/konveyor/rulesets/issues/291) | Expand actuator → Quarkus observability mapping | Research + one or many rules/messages |
| **4** | [#298](https://github.com/konveyor/rulesets/issues/298) | Devservices as local alternative for SQL/NoSQL/messaging | Many technology-specific dependency rules or one broad guide |
| **5** | [#288](https://github.com/konveyor/rulesets/issues/288) | Split rules: “pure Quarkus” vs “Quarkus Spring” | Product/structure + many rules |
| **5** | [#293](https://github.com/konveyor/rulesets/issues/293) | Sharpen generic catchall + dependency conversion table | Many specific rules or doc-heavy catchall; tests blocked |

---

## [#289](https://github.com/konveyor/rulesets/issues/289) — Review and improve `228-springboot-jmx-to-quarkus`

**Effort: 1 — Small**

### Original issue (summary)

- The rule **conflates JMX with GraalVM Native Image**; native image is not specific to Spring→Quarkus.
- If the goal is Spring→Quarkus **and** JMX/operational concerns, the guidance should align with **Micrometer / telemetry** on Quarkus, with links such as:
  - [Quarkus Micrometer guide](https://quarkus.io/guides/telemetry-micrometer)
  - [Quarkiverse Micrometer registry docs](https://docs.quarkiverse.io/quarkus-micrometer-registry)

### Current code (this repo)

[`stable/java/quarkus/228-springboot-jmx-to-quarkus.windup.yaml`](../stable/java/quarkus/228-springboot-jmx-to-quarkus.windup.yaml): two `mandatory` rules (XML `MBeanExporter`, annotations under `org.springframework.jmx.*`) with messages that emphasize **native image** rather than **JMX portability / observability alternatives**.

### What it would take to implement

1. **Rewrite `description`, `message`, and `effort`** so incidents explain:
   - JMX limitations on Quarkus (especially native) **as a JMX concern**, not “Spring migration = native.”
   - Practical alternatives: Micrometer metrics, health, optional JVM-mode notes if relevant.
2. **Add `links`** to the guides above (and optionally SmallRye Health if health is part of the story).
3. **Optional split:** separate “native” nuance from “JVM mode” in the message if you want precision without extra rules.
4. **Tests:** update [`tests/228-springboot-jmx-to-quarkus.windup.test.yaml`](../stable/java/quarkus/tests/228-springboot-jmx-to-quarkus.windup.test.yaml) expected messages/links.

Mostly editorial and link curation.

---

## [#290](https://github.com/konveyor/rulesets/issues/290) — Improve `225-springboot-di-to-quarkus`

**Effort: 3 — Medium**

### Original issue (summary)

- Change category from **`potential` to `mandatory`** for rule `springboot-di-to-quarkus-00000` (Spring Beans / `spring-di` extension).
- Add a rule (or rules) reflecting the **conversion table** from [Quarkus Spring DI – conversion table](https://quarkus.io/guides/spring-di#conversion-table).
- For unsupported types like `BeanPostProcessor`: don’t stop at “not supported”; point users to **CDI Extension** / `@Interceptor` / `@Decorator` patterns (with the table’s Spring vs CDI mapping).
- Similarly for **programmatic `ApplicationContext`** usage: document **`CDI.current()`** as the preferred approach (issue includes example snippets).

### Current code (this repo)

[`stable/java/quarkus/225-springboot-di-to-quarkus.windup.yaml`](../stable/java/quarkus/225-springboot-di-to-quarkus.windup.yaml):

- `springboot-di-to-quarkus-00000` is **`potential`**, triggers on `org.springframework.spring-beans`.
- `springboot-di-to-quarkus-00002` already flags `BeanPostProcessor` (and related) **implements** types with a short “will not be executed” message; **`ApplicationContext` is only mentioned in the message text**, not as a dedicated `when` condition.

### What it would take to implement

1. **One-line policy change:** set `category: mandatory` on `00000` (aligns with other “replace dependency” rules in this folder).
2. **Message and link upgrades for `00002`:** expand with CDI extension / interceptor / decorator guidance; add links to Spring DI guide sections.
3. **New conditions for `ApplicationContext`:** e.g. `java.referenced` on `org.springframework.context.ApplicationContext` (injection, implements, extends—need to avoid extreme noise; may scope to `IMPLEMENTS_TYPE` / `INHERITANCE` / method return types per analyzer capabilities).
4. **Conversion table:** either embed a **condensed** table in a single rule message or add **targeted** rules per high-value Spring stereotype (more work, clearer incidents).
5. **Tests:** extend [`tests/225-springboot-di-to-quarkus.windup.test.yaml`](../stable/java/quarkus/tests/225-springboot-di-to-quarkus.windup.test.yaml) and fixtures under `tests/data/225-springboot-di-to-quarkus/`.

Main cost is tuning `when` clauses to limit false positives and updating tests.

---

## [#292](https://github.com/konveyor/rulesets/issues/292) — Improve `220-springboot-annotations-to-quarkus`

**Effort: 3 — Medium**

### Original issue (summary)

- `@SpringBootApplication` composes three concerns (per [Spring Boot docs](https://docs.spring.io/spring-boot/reference/using/using-the-springbootapplication-annotation.html)):
  - `@EnableAutoConfiguration`
  - `@ComponentScan`
  - `@SpringBootConfiguration` (vs plain `@Configuration` for Boot-centric testing/config detection)
- The rule should explain how to achieve these in **Quarkus** (and/or Quarkus Spring), not only “remove annotation or optional Spring dependency.”

### Current code (this repo)

[`stable/java/quarkus/220-springboot-annotations-to-quarkus.windup.yaml`](../stable/java/quarkus/220-springboot-annotations-to-quarkus.windup.yaml): single rule on `@SpringBootApplication` with the “remove or optional `spring-boot-autoconfigure`” message.

### What it would take to implement

1. **Rewrite the main message** into sections: auto-configuration → Quarkus extensions/build-time model; component scan → Quarkus bean discovery / Jandex / [`@ApplicationScoped`](https://quarkus.io/guides/cdi-reference) patterns; `@SpringBootConfiguration` / `@Configuration` → CDI `@ApplicationScoped` + Quarkus testing guidance.
2. **Optional additional rules** with `java.referenced` on `EnableAutoConfiguration`, `ComponentScan`, `SpringBootConfiguration` for users who use them **without** `@SpringBootApplication`.
3. **Links:** Quarkus CDI, Quarkus Spring (if documenting compatibility path), testing guide.
4. **Tests:** update [`tests/220-springboot-annotations-to-quarkus.windup.test.yaml`](../stable/java/quarkus/tests/220-springboot-annotations-to-quarkus.windup.test.yaml).

Mostly documentation quality; extra rules add test surface.

---

## [#294](https://github.com/konveyor/rulesets/issues/294) — Spring Cloud Consul → Quarkus Stork

**Effort: 3 — Medium**

### Original issue (summary)

- Add a **new rule** for migrating from **Spring Cloud Consul** to **Quarkus Stork**, referencing [Stork guide](https://quarkus.io/guides/stork).

### What it would take to implement

1. **Identify coordinates:** e.g. `org.springframework.cloud:spring-cloud-starter-consul-*`, `spring-cloud-consul-discovery`, etc. (align with versions used in Spring Cloud BOMs).
2. **New rule file** under `stable/java/quarkus/` (e.g. `238-springboot-cloud-consul-to-quarkus-stork.windup.yaml` or similar; follow next free numeric convention per CONTRIBUTING).
3. **Message content:** Stork + Consul client configuration at high level; link to Stork; note that this is **architectural** (service discovery/load balancing), not a drop-in API swap.
4. **Tests:** sample `pom.xml` under `tests/data/...` and `.test.yaml` asserting incidents.
5. **Overlap:** ensure no conflict with generic Spring Cloud rules if any exist.

Pattern matches existing files like [`222-springboot-cloud-config-client-to-quarkus.windup.yaml`](../stable/java/quarkus/222-springboot-cloud-config-client-to-quarkus.windup.yaml).

---

## [#295](https://github.com/konveyor/rulesets/issues/295) — Container image build guidance (Quarkus)

**Effort: 3 — Medium**

### Original issue (summary)

- Add rules explaining how to **build container images** with Quarkus per [container-image guide](https://quarkus.io/guides/container-image).
- Quarkus supports **multiple** approaches (Buildpack, OpenShift S2I, Jib, Docker, etc.); **several rules** may be appropriate (to be evaluated).

### What it would take to implement

1. **Trigger strategy:** These are often **not** Spring-specific; rules might key off:
   - `quarkus-container-image-*` extensions already in POM, or
   - presence of `docker-maven-plugin` / `jib-maven-plugin` / OpenShift plugin, or
   - generic “Spring Boot app migrating to Quarkus” + Dockerfile present.
2. **One file or several:** e.g. separate rules per build strategy with `links` to the right subsection of the guide.
3. **Tests:** minimal POMs and optional `Dockerfile` snippets in `tests/data/`.
4. **Product fit:** decide whether this belongs in **Spring→Quarkus** ruleset only or a **general Quarkus** ruleset (if one exists or gets added).

Scope depends on number of strategies and triggers.

---

## [#291](https://github.com/konveyor/rulesets/issues/291) — Improve `219-springboot-actuator-to-quarkus`

**Effort: 4 — Medium–large**

### Original issue (summary)

- Replacing Actuator with **SmallRye Health** alone is **insufficient**; Actuator exposes many endpoints (beans, caches, health, info, conditions, configprops, env, loggers, heapdump, threaddump, metrics, scheduledtasks, mappings, …).
- Desired: **map** what Actuator exposes to what Quarkus offers (health, metrics, config, logging, etc.).

### Current code (this repo)

[`stable/java/quarkus/219-springboot-actuator-to-quarkus.windup.yaml`](../stable/java/quarkus/219-springboot-actuator-to-quarkus.windup.yaml): dependency-based rules for actuator artifacts + a **properties** rule mapping `management.endpoints.web.exposure.include` health exposure to `quarkus.smallrye-health.root-path`.

### What it would take to implement

1. **Research matrix:** For each Actuator endpoint category, document Quarkus equivalents (e.g. SmallRye Health, Micrometer/Prometheus, logging config, `quarkus.arc` dev mode, etc.) and **honest gaps** (e.g. heap dump may be “use JDK tools / platform”, not a single extension).
2. **Implementation options:**
   - **A)** One rich `message` on the main actuator dependency rule with a structured table (fastest, one incident).
   - **B)** Multiple rules: `java.dependency` + optional `builtin.filecontent` for `management.endpoint.*.enabled` to suggest specific extensions (more precise, more YAML and tests).
3. **Links:** SmallRye Health, Micrometer, possibly management/observability guides.
4. **Tests:** new or updated fixtures with `application.properties`/`application.yml` and expected incidents.

Depends on A vs B; the research and keeping the matrix accurate across Quarkus versions is ongoing maintenance.

---

## [#298](https://github.com/konveyor/rulesets/issues/298) — Devservices for local SQL, NoSQL, messaging, etc.

**Effort: 4 — Medium–large**

### Original issue (summary)

- Propose **Quarkus Dev Services** (and related dependencies) as an alternative to running local infrastructure via containers manually—covering **SQL, NoSQL, messaging**, etc.

### What it would take to implement

1. **Breadth:** Either:
   - **Many small rules:** `java.dependency` on `spring-boot-starter-data-jpa` + JDBC driver → suggest `quarkus-jdbc-*` + dev services; same idea for Kafka (`spring-kafka` → `quarkus-messaging-kafka`), Mongo, Redis, etc., or
   - **A few umbrella rules** with long messages listing categories and linking to [Dev Services overview](https://quarkus.io/guides/dev-services) (less precise).
2. **Accuracy:** Dev Services require **specific** Quarkus extensions; messages must avoid promising dev services for stacks Quarkus doesn’t auto-start for that extension.
3. **Overlap:** Spring→Quarkus rules already exist for JPA, cache, etc.; add **devservices** as an extra paragraph or **child rule** to avoid contradiction.
4. **Tests:** per dependency fixture or consolidated sample apps.

If done comprehensively, larger than a single new rule file; smaller if limited to top 5–10 Spring starters.

---

## [#288](https://github.com/konveyor/rulesets/issues/288) — Split the rules into two migration paths (Quarkus vs Quarkus Spring)

**Effort: 5 — Large**

### Original issue (summary)

- Today a user may migrate Spring (Boot) either to **idiomatic Quarkus** or to **Quarkus with the Spring compatibility layer** (“Quarkus Spring”).
- Proposed: two **families** of rules:
  - Spring(Boot) → **Quarkus Spring**
  - Spring(Boot) → **Quarkus** (native idioms)
- **Common** rules (e.g. POM parent/BOM detection) would live in a **`shared`** or **`common`** folder.

### What it would take to implement

1. **Design agreement (outside YAML-only work)**  
   - How Konveyor users **select** one path vs the other (separate ruleset bundles, labels, tags, or analyzer configuration).  
   - Whether **both** paths can run together without duplicate/conflicting incidents.

2. **Repository layout**  
   - New directories under `stable/java/` (e.g. `quarkus-spring/` vs `quarkus/`) or subfolders under `quarkus/` with clear naming.  
   - CONTRIBUTING-style updates so contributors know where to add rules.

3. **Rule audit and duplication**  
   - Roughly **35** Spring→Quarkus rule files today; many are already “use Quarkus extension X” vs “rewrite to Y.” Each would need classification into **path A**, **path B**, or **shared**.  
   - **Shared** rules: dependency-only checks (e.g. parent POM) are easier; rules that assume **removal** of Spring APIs vs **optional Spring** dependency need splitting or conditional messaging (if the engine supports it cleanly).

4. **Labels and metadata**  
   - Consistent `labels` / tags so UIs and CI can filter by migration strategy.

5. **Tests**  
   - Every moved or duplicated rule needs `tests/` and `tests/data/` aligned with the new layout; CI runs `kantra test` per changed files.

**Risk:** Highest scope and process cost; easy to end up with duplicated maintenance unless the split is strictly mechanical (copy + tweak messages).

---

## [#293](https://github.com/konveyor/rulesets/issues/293) — `226-springboot-generic-catchall`: precision + conversion table

**Effort: 5 — Large**

### Original issue (summary)

- Many rules already cover specific Spring features; the **catchall** scans POMs for `{group}:{artifact}` but is **generic**—hard for users to act on.
- Ask: make the rule **more precise** and add a **conversion table** mapping Spring dependencies to recommended Quarkus extensions/features.

### Current code (this repo)

[`stable/java/quarkus/226-springboot-generic-catchall.windup.yaml`](../stable/java/quarkus/226-springboot-generic-catchall.windup.yaml): single `potential` rule with `java.dependency` `name: '{group}.{artifact}'` and templated `{{group}}:{{artifact}}` in the message.

[`tests/226-springboot-generic-catchall.windup.test.yaml`](../stable/java/quarkus/tests/226-springboot-generic-catchall.windup.test.yaml): **fully commented** with `# FIXME: requires ability to match dependencies using regex`.

### What it would take to implement

1. **Precision strategies:**
   - **Replace catchall** with a **long list** of explicit `java.dependency` rules for known Spring artifacts not covered elsewhere (high maintenance, best UX).
   - Or **narrow** the catchall with **exclusions** (if the analyzer supports negation / lists)—depends on [analyzer-lsp rule capabilities](https://github.com/konveyor/analyzer-lsp/blob/main/docs/rules.md).
2. **Conversion table:** large curated map (Spring Boot starters → Quarkus extensions); overlaps with existing rules—need deduplication to avoid **double incidents**.
3. **Testing:** today’s tests are blocked; implementing reliable tests may require **upstream rule engine** features or abandoning the generic dependency template.

Core difficulty is **scalable maintenance** and **testability** of a generic dependency matcher.

---

## Cross-cutting recommendations

1. **Path alignment:** Update issue links or comments to point at `stable/java/quarkus/` when contributing here, so implementers don’t edit only generated output.
2. **Ordering:** Sections above follow **ascending effort** (rank 1 → 5), then issue number within the same rank. Quick wins cluster at **#289**; **#288** and **#293** need design before large edits.
3. **Blocked tests:** **#293** is tied to generic dependency matching; confirm analyzer support before investing in a huge dependency matrix.

---

*Generated as a planning aid; effort ranks are estimates and assume familiarity with Konveyor rule YAML and `kantra test`.*
