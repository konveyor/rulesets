# Skill: Add or update tests for an existing rule

Use this workflow when the user asks to add tests or fix tests for a rule.

## Steps

1. **Locate the rule file and its test file**
   - Rule file: e.g. `stable/java/spring-boot/spring-boot-2.x-to-3.0-actuator.yaml`.
   - Test file: **same directory**, **`tests/<rule-file-basename>.test.yaml`** (e.g. `tests/spring-boot-2.x-to-3.0-actuator.test.yaml`).

2. **If the test file doesn’t exist: create it**
   - **`rulesPath`**: Relative path to the rule file (e.g. `../spring-boot-2.x-to-3.0-actuator.yaml`).
   - **`providers`**: Match the rule’s `when`:
     - If the rule uses `java.referenced` or `java.dependency` → include `name: java` and `dataPath` (e.g. `./data/actuator`).
     - If the rule uses `builtin.filecontent` → include `name: builtin` and the same (or appropriate) `dataPath`.
   - **`tests`**: List of entries, one per rule you want to test (add the new rule’s `ruleID` and test cases).

3. **Add or update the entry for the rule’s `ruleID`**
   - Under **`tests`**, add an item with **`ruleID`** (must match the rule exactly) and **`testCases`**.
   - Each test case:
     - **`name`**: Folder name under `data/<provider-dataPath>/<name>/` (e.g. `tc-custom-endpoint`).
     - **`analysisParams`**: e.g. `mode: "source-only"`.
     - **`hasIncidents`**: `exactly: N`, `atLeast: N`, or `atMost: N` (use `atLeast: 0` if the rule may not fire).

4. **Ensure test data exists**
   - Under **`tests/data/<provider-dataPath>/<test-case-name>/`** (e.g. `tests/data/actuator/tc-custom-endpoint/`).
   - For **java**: include Java sources (and optionally `pom.xml` / `build.gradle`) so the rule’s `when` can match. The sources must have a valid pom.xml and be a compilable Java project.
   - For **builtin**: include whatever files (e.g. `application.properties`) that the rule’s `builtin.filecontent` expects.
   - If the rule uses **both** Java and filecontent, both providers should point at the **same** data directory so one test case’s folder contains both Java and config files.

## Reference

- Test format: see **test-format-and-conventions** (`.cursor/rules/`).
- CI expects test file at `tests/<rule-file-basename>.test.yaml`, see `.github/workflows/local-ci.yaml`.
