#!/bin/bash

# Rule: build-pattern-00020
export GOEXPERIMENT=boringcrypto

# Rule: build-pattern-00030
export GOFIPS=1

# Rule: build-pattern-00040
go build -tags=requirefips ./...
