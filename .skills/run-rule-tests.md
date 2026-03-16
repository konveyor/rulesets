# Skill: Run the rule tests

Use this workflow when the user asks to run tests locally or wants to understand how CI runs them.

## Local: install Kantra and run tests

1. **Install Kantra**
   - Follow [Kantra rules-quickstart](https://github.com/konveyor/kantra/blob/main/docs/rules-quickstart.md#how-to-install-kantra).

2. **Run tests**
   - CI mirrors the expected layout: rules under `stable/` and `preview/` are copied to `~/.config/.kantra/rulesets/stable` and `rulesets/preview`, then **`kantra test`** is run.
   - **All tests:** From the repo root, after arranging rules under `~/.config/.kantra/rulesets/`, run something like:
     - `JVM_MAX_MEM=3072m RUN_LOCAL=1 kantra test ./rulesets` (or the path where Kantra expects the rulesets).
   - **Specific test files:** Run `kantra test <path-to-test-file(s)>` with the test YAML path(s). CI uses `./kantra test <test-file-list>` for changed-only runs.
   - Exact local command may depend on how Kantra is installed (e.g. from container or binary); see Kantra docs for the current CLI.

## CI: how tests are triggered

- **`.github/workflows/local-ci.yaml`** runs on push, pull_request, schedule (nightly), and workflow_dispatch.
- **Changed rules:** CI finds changed files under `stable/` or `preview/` (`.yaml`, `.windup.yaml`, `.mta.yaml`), excluding `*.test.yaml` and `ruleset.yaml`. For each changed **rule file**, it looks for the test file at **`<rule-dir>/tests/<basename>.test.yaml`** (with special handling for `.windup.yaml` → `.windup.test.yaml`, `.mta.yaml` → `.test.yaml`). It runs only those test files.
- **Changed test files:** If a `*.test.yaml` file is changed, that test file is run directly.
- **All tests:** On schedule or manual **workflow_dispatch**, CI runs all tests (e.g. `kantra test ./rulesets`).
- **No changes:** If no rule or test files under `stable/` or `preview/` changed, the test job is skipped.

## Reference

- Workflow: `.github/workflows/local-ci.yaml`
- Kantra: [rules-quickstart](https://github.com/konveyor/kantra/blob/main/docs/rules-quickstart.md)
