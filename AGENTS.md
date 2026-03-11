# Konveyor Rulesets – context for AI agents

This repository contains **Konveyor rulesets**: YAML rules for static code analysis, used by [konveyor/analyzer-lsp](https://github.com/konveyor/analyzer-lsp) to help users modernize applications (e.g. Java to Quarkus/Spring Boot, .NET, Node.js).

## Layout

- **Rules** live under **`stable/`** and **`preview/`**, organized by language and technology (e.g. `stable/java/spring-boot/`, `stable/java/quarkus/`, `stable/dotnet/`, `stable/nodejs/`).
- Each technology directory has rule YAML files and a **`tests/`** directory. Test files are named **`<rule-file>.test.yaml`** and live under `tests/`; test data lives under **`tests/data/`**.
- Rule schema and conventions: [analyzer-lsp rules](https://github.com/konveyor/analyzer-lsp/blob/main/docs/rules.md).

## When editing rules or tests

- **Contributing:** See **CONTRIBUTING.md** for directory structure, rule IDs, test file naming, and conventions.
- **Workflows:** See **`.skills/`** for step-by-step skills (create new rule, add/update tests, run tests). These follow the open spec so different tools can discover them.

*Cursor users: detailed rule and test schema are in `.cursor/rules/` (rule-format-and-conventions, test-format-and-conventions).*

## Running tests

Tests are run by **Kantra** (`kantra test`). CI is in **`.github/workflows/local-ci.yaml`**: it finds changed rule files under `stable/` or `preview/` and runs the corresponding test files. Use the **run-rule-tests** skill in `.skills/` for the exact workflow.
