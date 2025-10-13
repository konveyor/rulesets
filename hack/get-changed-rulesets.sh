#!/bin/bash
# Script to identify changed rulesets in a PR and determine which tests to run

set -e

# Get the base branch (usually main)
BASE_BRANCH=${1:-origin/main}

# Function to get the parent directory (ruleset) of a changed file
get_ruleset_dir() {
    local file=$1

    # If the file is in default/generated/*/...
    if [[ "$file" =~ ^default/generated/([^/]+)/ ]]; then
        echo "default/generated/${BASH_REMATCH[1]}"
    fi
}

# Get list of changed files compared to base branch
echo "Comparing against: $BASE_BRANCH" >&2

# Get changed files
CHANGED_FILES=$(git diff --name-only "$BASE_BRANCH"...HEAD 2>/dev/null || git diff --name-only HEAD~1...HEAD)

# Track unique ruleset directories
declare -A RULESETS

# Process each changed file
while IFS= read -r file; do
    # Skip if file doesn't exist or is deleted
    [ -e "$file" ] || continue

    # Check if it's a rule file or test file
    if [[ "$file" =~ \.yaml$ ]] && [[ "$file" =~ ^default/generated/ ]]; then
        RULESET_DIR=$(get_ruleset_dir "$file")
        if [ -n "$RULESET_DIR" ]; then
            RULESETS["$RULESET_DIR"]=1
        fi
    fi
done <<< "$CHANGED_FILES"

# Check if any critical files were changed that require full test run
CRITICAL_FILES_CHANGED=false
while IFS= read -r file; do
    # Check for changes to CI/CD, scripts, or root config files
    if [[ "$file" =~ ^\.github/workflows/ ]] || \
       [[ "$file" =~ ^hack/ ]] || \
       [[ "$file" == "CLAUDE.md" ]] || \
       [[ "$file" == "README.md" ]] || \
       [[ "$file" == "CONTRIBUTING.md" ]]; then
        CRITICAL_FILES_CHANGED=true
        break
    fi
done <<< "$CHANGED_FILES"

# If critical files changed, run all tests
if [ "$CRITICAL_FILES_CHANGED" = true ]; then
    echo "Critical files changed - will test all rulesets" >&2
    echo "./default/generated"
elif [ ${#RULESETS[@]} -eq 0 ]; then
    echo "No ruleset changes detected" >&2
    echo ""
else
    echo "Detected changes in ${#RULESETS[@]} ruleset(s):" >&2
    for ruleset in "${!RULESETS[@]}"; do
        echo "  - $ruleset" >&2
        echo "$ruleset"
    done | sort
fi