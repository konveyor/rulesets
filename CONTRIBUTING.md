# Contributing to Konveyor Rulesets

Thank you for considering contributing to the Konveyor Rulesets project! We welcome all contributions—whether they’re issues, code, documentation, or suggestions for new features. Below, you’ll find our guidelines for contributing.

## Table of Contents
1. [Code of Conduct](#code-of-conduct)
2. [How Can You Contribute?](#how-can-you-contribute)
3. [Getting Started](#getting-started)
4. [Submitting an Issue](#submitting-an-issue)
5. [Submitting a Pull Request](#submitting-a-pull-request)
6. [Coding Guidelines](#coding-guidelines)
7. [Commit Message Guidelines](#commit-message-guidelines)
8. [Setting Up the Environment](#setting-up-the-environment)
9. [Resources](#resources)

---

### Code of Conduct
This project follows Konveyor's [Code of Conduct](https://github.com/konveyor/community/blob/e55cfd83fc34bc2392306301da884fb4b702f480/CODE_OF_CONDUCT.md). By participating, you agree to abide by its terms.

### How Can You Contribute?
Here are some ways you can help:
- **New rules**: You can contribute to the Rulesets project with any new rules you might have come up with for doing
your own migrations, as long as they are related to open-source technologies and you follow the guidelines in this
document.
- **Feature Requests**: You can request new rules for existing technologies not yet covered by our rulesets.
- **Bug fixes**: Fix bugs in rules, or create new tests for uncovered scenarios.
- **Documentation**: Improve our documentation, tutorials, or guides.

### Getting Started
1. **Fork the repository** and create a new branch from `main`.
2. **Follow these instructions** to install Kantra: [Setting up Kantra and developing rules](https://github.com/konveyor/kantra/blob/main/docs/rules-quickstart.md#how-to-install-kantra).
3. Make your changes according to the [Rule creation guidelines](#4-rule-creation-guidelines), test them, and commit your work.

### Rule creation guidelines
There are a few conventions that we follow when creating rules:

#### Directory structure
Rules can be found within the `default/generated` folder. Within this folder, rules are organized by target technology.
**_Source_** and **_target_** technologies are the technologies to migrate **_to_** and **_from_**, respectively.
If a new target technology is to be added, a new folder should be created to cover for it. Within each target directories,
additional directories can be created to further segregate the rules according to their source technologies. For instance,
Within the `eap8` target folder, we could have `eap6` and `eap7` folders covering for migration to eap8 from eap 6 and 7
respectively.

Additionally, within each rules directory, a `test` directory should be created to place the rules's tests. Since each
test needs data, a `data` directory must also be created inside `test`. For instance:

```shell
.
└── target
    ├── source1
    │   ├── rules-1.yaml
    │   ├── rules-2.yaml
    │   └── tests
    │       ├── data
    │       │   ├── rules-1
    │       │   └── rules-2
    │       ├── rules-1.test.yaml
    │       └── rules-2.test.yaml
    └── source2
        └── ...
```
Inside of each `data` folder, it is recommended to segregate the test data, so that each folder within it contains the
data for the tests of a single rules file. This way, other tests will be less affected by changes in the data.

#### Tests organization and file naming
- It is recommended that a single rules file holds rules for the migration of a related set of concerns within its particular
migration path. For instance, if migration from `A` to `B` implies changes related to data sources and web services,
two different rule files should be created, one containing the rules related to data sources and another one for web services. 
This makes it easier to find rules, create tests and data for them, and keep the structure organized in general.

- Rules should be named using hypens for word separation. Also, names for the rule files should indicate the target
technology and the concern of the rules they contain. If needed, they can also indicate the source technology.

- Test files should be named after the rule file they cover in the following way: `<rule-file>.test.yaml`

#### Rule IDs
Rule IDs must be unique within the rulesets being used, and contain in themselves the name of the rule file plus a
number. These numbers must have 5 digits and increase by 10 each time, to follow a convention set in the old Windup rules.
For instance, within a `rules-eap-web.yaml` file:
```yaml
- ruleID: rules-eap-web-00001
  ...
- ruleID: rules-eap-web-00010
  ...
```


### Submitting an Issue
- Search existing issues to avoid duplicates.
- Provide as much detail as possible, including steps to reproduce.
- Label the issue appropriately (bug, enhancement, question, etc.).

### 5. Submitting a Pull Request
- Ensure your branch is up-to-date with `main`.
- Make sure all tests pass before submitting.
- Include a detailed description of your changes.
- Reference related issues in the description (e.g., `Fixes #123`).
- Follow our [Commit Message Guidelines](#commit-message-guidelines).

### 6. Coding Guidelines
- **Formatting**: Follow [Project Style Guide](./STYLE_GUIDE.md) for consistent coding practices.
- **Naming Conventions**: Use clear, descriptive names.
- **Documentation**: Document new methods, classes, and changes to existing functions.
- **Testing**: Write unit tests for new features and bug fixes.

### 7. Commit Message Guidelines
1. Use the present tense ("Add feature" not "Added feature").
2. Keep messages concise but informative (max 72 characters).
