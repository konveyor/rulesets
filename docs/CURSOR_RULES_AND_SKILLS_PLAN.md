# Plan: Cursor Rules and Skills for Konveyor Rulesets

This document outlines what would be done to add **Cursor rules** (reference context) and **Cursor skills** (task workflows) to this repository. No rules or skills are created by this plan; it is a design and scoping note for when we implement them.

---

## 1. What This Repo Is (Summary)

- **Purpose:** Konveyor rulesets for static code analysis, compatible with [konveyor/analyzer-lsp](https://github.com/konveyor/analyzer-lsp). Rules help users modernize applications (e.g. Java to Quarkus/Spring Boot, .NET, Node.js).
- **Layout:** Rules live under `stable/` and `preview/` (by language/tech: `stable/java/`, `stable/dotnet/`, `stable/nodejs/`, `preview/`). Each technology has one or more rule files (YAML) and a `tests/` directory beside them. Test data lives under `tests/data/<name>/` (e.g. Java source, config files).
- **Tooling:** Tests are run by **Kantra** (`kantra test`). CI is in `.github/workflows/local-ci.yaml`: it discovers changed rule/test files and runs the corresponding tests (or all tests on schedule/manual dispatch). Local setup is documented in CONTRIBUTING (install Kantra from [rules-quickstart](https://github.com/konveyor/kantra/blob/main/docs/rules-quickstart.md)).
- **Docs today:** CONTRIBUTING.md describes directory structure, rule IDs, and test file naming; it still references `default/generated`, but the repo has moved to `stable/` and `preview/`. README explains the project and points to analyzer-lsp. There is no STYLE_GUIDE.md (referenced in CONTRIBUTING). No single doc describes the full rule YAML schema or test YAML schema in one place.

---

## 2. Goals for Rules and Skills

- **Rules:** Give the AI consistent reference when editing rule or test YAML: schema, conventions, what works and what doesn’t, and where things live.
- **Skills:** Give the AI step-by-step workflows for: creating a new rule (and optional test), adding/updating tests for a rule, and running the tests (locally or via CI).

---

## 3. Proposed Cursor Rules (Reference)

Rules would live in `.cursor/rules/` as `.mdc` files so they can be auto-attached when relevant. Each rule would have a clear `description` and `globs` where useful.

### 3.1 Rule: “Rule file format and conventions” (rule YAML)

- **Purpose:** Reference for creating or editing rule YAML files.
- **Activation:** Auto-attach when editing rule files. Use globs such as `stable/**/*.yaml`, `preview/**/*.yaml`, and exclude `*.test.yaml` and `ruleset.yaml` so we don’t attach when editing tests or ruleset metadata.
- **Content to include:**
  - **Location:** Rules live under `stable/<language>/<technology>/` or `preview/...`. One file per concern (e.g. `spring-boot-2.x-to-3.0-actuator.yaml`). Each directory can have a `ruleset.yaml` (name, description).
  - **Schema (per rule entry):** `ruleID`, `category`, `effort`, `labels`, `when`, `description`, `message`, `links`. Rule IDs must be unique; convention in this repo is `<ruleset-prefix>-<5-digit-number>` (e.g. `spring-boot-2.x-to-3.0-actuator-00010`), incrementing by 10.
  - **Categories:** e.g. `mandatory`, `optional`, `potential` (as used in the codebase).
  - **When conditions:** Document the main providers and patterns used in this repo:
    - `builtin.filecontent`: `pattern` (text/regex), `filePattern` (e.g. `".*\\.properties"`, `".*\\.(yml|yaml)"`). Use escaped dot in filePattern when matching extensions.
    - `java.referenced`: `pattern` (class/annotation), `location` (e.g. `ANNOTATION`, `IMPLEMENTS_TYPE`).
    - `java.dependency`: `name`/`nameregex`, `upperbound` (version).
    - Combine with `and`, `or` as needed.
  - **What works / what doesn’t:** (1) Use quoted `filePattern` with regex like `".*\\.properties"` for consistency. (2) Prefer one concern per rule file to keep tests and data manageable. (3) CONTRIBUTING says “default/generated” but the repo uses `stable/` and `preview/` — follow the current layout. (4) Add migration-guide or doc links in `links` where relevant.

### 3.2 Rule: “Test file format and conventions” (test YAML)

- **Purpose:** Reference for creating or editing test YAML files.
- **Activation:** Auto-attach when editing test files, e.g. globs: `**/*.test.yaml`, or `stable/**/tests/*.yaml`, `preview/**/tests/*.yaml`.
- **Content to include:**
  - **Location:** For a rule file `foo.yaml`, the test file is `tests/foo.test.yaml` in the same directory. Test data goes under `tests/data/<logical-name>/` (e.g. `tests/data/actuator/`, `tests/data/core-changes/`). Each test file declares `rulesPath` (relative to test file, e.g. `../spring-boot-2.x-to-3.0-actuator.yaml`).
  - **Schema:** Top-level `rulesPath`, `providers` (e.g. `java`, `builtin` with `dataPath`), and `tests` (list of entries per rule). Each entry: `ruleID`, `testCases`. Each test case: `name`, `analysisParams` (e.g. `mode: "source-only"`), and `hasIncidents` with `exactly: N` or `atLeast: N` (and possibly `atMost` if used).
  - **Providers:** Match the rule’s `when` (e.g. Java rules need `java` provider pointing at Java sources; filecontent rules need `builtin` pointing at the same data directory that contains the config files).
  - **What works / what doesn’t:** (1) Test case names (e.g. `tc-custom-endpoint`) are used to select the folder under `data/<provider-dataPath>/<test-case-name>/`. (2) Use `atLeast: 0` when a rule may or may not fire on a given input. (3) One test file per rule file keeps CI’s “find test for changed rule” logic simple (see local-ci.yaml).

---

## 4. Proposed Cursor Skills (Workflows)

Skills live in **`.skills/`** at the repo root, following the open spec so different implementors can discover them. Each skill is a markdown workflow the AI can follow when the user asks for that task.

### 4.1 Skill: “Create a new rule (and optional test)”

- **Trigger:** User asks to add a new rule (or rules) for a technology.
- **Steps the skill would describe:**
  1. Identify the right directory under `stable/<language>/<technology>/` or `preview/...` (create subdir if new technology).
  2. Choose or create a rule file (one file per concern; name like `tech-version-concern.yaml`).
  3. Assign the next rule ID (same prefix, next 5-digit number, step 10).
  4. Add the rule entry: category, effort, labels, `when`, description, message, links.
  5. If the rule is Java-based: ensure `when` uses `java.referenced` or `java.dependency` and that provider exists; if file-based: use `builtin.filecontent` with appropriate `filePattern`.
  6. Optionally create a test file and test data: create `tests/<rule-file-basename>.test.yaml`, set `rulesPath` and `providers`/`dataPath`, add a test entry for the new ruleID and at least one test case with `name`, `analysisParams`, `hasIncidents`; add minimal data under `tests/data/<name>/` (e.g. a small Java project or config files) so the test can run.

### 4.2 Skill: “Add or update tests for an existing rule”

- **Trigger:** User asks to add tests or fix tests for a rule.
- **Steps:**
  1. Locate the rule file and its test file (same directory, `tests/<rule-file>.test.yaml`).
  2. If the test file doesn’t exist: create it with `rulesPath`, `providers` (matching the rule’s `when`), and `tests` list.
  3. Add or update an entry for the rule’s `ruleID` with `testCases`. Each case: `name` (folder under `data/<provider>/`), `analysisParams`, `hasIncidents` (exactly/atLeast/atMost).
  4. Ensure test data exists under `tests/data/<provider-dataPath>/<test-case-name>/` (e.g. Java sources for `java`, config files for `builtin`). If the rule uses both Java and filecontent, both providers should point at the same data directory so the same inputs are used.

### 4.3 Skill: “Run the rule tests”

- **Trigger:** User asks to run tests (locally or understand how CI runs them).
- **Steps:**
  1. **Local:** Follow [Kantra rules-quickstart](https://github.com/konveyor/kantra/blob/main/docs/rules-quickstart.md) to install Kantra. Then run tests for specific test files or the whole repo. The exact command mirrors CI: rules are under `~/.config/.kantra/rulesets/stable` (and `preview`); run `kantra test <paths>` with the test YAML path(s). CI uses `JVM_MAX_MEM=3072m RUN_LOCAL=1 ./kantra test ./rulesets` for “all” or `./kantra test <test-file-list>` for changed-only.
  2. **CI:** Push or open a PR; `.github/workflows/local-ci.yaml` finds changed rule files under `stable/` or `preview/`, maps each to `tests/<basename>.test.yaml`, and runs those test files. Nightly or manual dispatch runs all tests.

---

## 5. Gaps and Caveats

- **CONTRIBUTING.md** still says rules are in `default/generated` and “test” (singular) directory; the repo actually uses `stable/` and `preview/` and `tests/` (plural). Any rules/skills should state the current layout and, if we update CONTRIBUTING, align with that.
- **STYLE_GUIDE.md** is referenced in CONTRIBUTING but does not exist; no need to mention it in rules until it’s added.
- **Rule schema** is inferred from existing YAML and analyzer-lsp compatibility; the canonical spec is in [analyzer-lsp rules](https://github.com/konveyor/analyzer-lsp/blob/main/docs/rules.md). Rules/skills could point to that for authoritative detail.
- **Kantra** is the runtime; local “how to run” depends on Kantra’s install and CLI. The skill would summarize the CI flow and point to the Kantra docs rather than duplicate them.
- **Windup vs YAML:** Some legacy rules may still be in Windup format (`.windup.yaml`); CI maps those to `.windup.test.yaml`. The plan above focuses on the main YAML rule/test format used in stable/java and similar trees.

---

## 6. Suggested File Layout (When Implemented)

```
.cursor/
  rules/
    rule-format-and-conventions.mdc   # globs: stable/**/*.yaml, preview/**/*.yaml (exclude *.test.yaml, ruleset.yaml)
    test-format-and-conventions.mdc  # globs: **/*.test.yaml or **/tests/*.yaml
.skills/                              # open spec: implementors look here for skills
  create-new-rule.md                  # workflow: create rule and test
  add-or-update-tests.md              # workflow: add/update tests for a rule
  run-rule-tests.md                   # workflow: run tests locally / CI
```

No AGENTS.md is proposed here; the rules above are scoped by globs and descriptions so the right context loads when editing rules or tests. If we later want a short “project overview” that’s always available, we could add a small AGENTS.md or an always-applied rule.

---

## 7. Next Steps (When Implementing)

1. Create `.cursor/rules/` and **`.skills/`** (skills in `.skills/` per open spec for implementor discovery).
2. Add the two rule files with the content and globs described in §3.
3. Add the three skill files with the step-by-step workflows in §4.
4. Optionally update CONTRIBUTING.md to use `stable/` and `preview/` and point to `.cursor/rules/` or this doc for “how the AI gets context.”
5. Test by asking Cursor to create a dummy rule, add a test, and run tests; adjust rules/skills if the AI misses steps or context.
