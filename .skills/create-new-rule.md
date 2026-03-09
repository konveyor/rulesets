# Skill: Create a new rule (and test)

Use this workflow when the user asks to add a new rule (or rules) for a technology. Each new rule should have a corresponding test.

## Steps

1. **Identify the right directory**
   - Under **`stable/<language>/<technology>/`** or **`preview/...`** (e.g. `stable/java/spring-boot/`, `stable/dotnet/`).
   - If the technology or migration path doesn’t exist, create a new subdirectory.

2. **Choose or create a rule file**
   - One file per concern. Name like `tech-version-concern.yaml` (e.g. `spring-boot-2.x-to-3.0-actuator.yaml`).
   - If adding to an existing file, use the same naming; if it’s a new concern, create a new file.

3. **Assign the next rule ID**
   - Same prefix as other rules in that file; 5-digit number; increment by 10 (e.g. `00010`, `00020`, `00030`).

4. **Add the rule entry**
   - Include: `ruleID`, `category`, `effort`, `labels`, `when`, `description`, `message`, and optionally `links`.
   - Categories: `mandatory`, `optional`, or `potential`.
   - Labels: e.g. `konveyor.io/source=...`, `konveyor.io/target=...`.

5. **Define the `when` condition**
   - **Java-based rule:** Use `java.referenced` (pattern, location) or `java.dependency` (name/nameregex, upperbound). Ensure the corresponding provider will be available in tests (java).
   - **File-based rule:** Use `builtin.filecontent` with `pattern` and `filePattern`. Use quoted `filePattern` with escaped dot, e.g. `".*\\.properties"`, `".*\\.(yml|yaml)"`.

6. **Create a test file and test data**
   - Create **`tests/<rule-file-basename>.test.yaml`** in the same directory as the rule file.
   - Set **`rulesPath`** to the rule file (e.g. `../my-rules.yaml`).
   - Set **`providers`** to match the rule (e.g. `java` and/or `builtin`) with **`dataPath`** pointing at a data directory (e.g. `./data/my-concern`).
   - Add a **`tests`** entry with the new **`ruleID`** and at least one **test case**: `name`, `analysisParams` (e.g. `mode: "source-only"`), `hasIncidents` (e.g. `atLeast: 1` or `exactly: N`).
   - Add **test data** under **`tests/data/<dataPath>/<test-case-name>/`** (e.g. a Maven buildable Java project or additional config/text files) so the test can run.

## Reference

- Rule format: see **rule-format-and-conventions** (`.cursor/rules/`).
- Test format: see **test-format-and-conventions** (`.cursor/rules/`).
- Add or update tests skill: see **add-or-update-tests.md** (`.skills/`).
- Canonical rule schema: [analyzer-lsp rules](https://github.com/konveyor/analyzer-lsp/blob/main/docs/rules.md).
