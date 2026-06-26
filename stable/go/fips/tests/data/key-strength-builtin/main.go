package main

import (
	"crypto/rand"
	"crypto/rsa"
	"fmt"
)

func main() {
	// Rule: key-strength-pattern-00010
	privateKey, _ := rsa.GenerateKey(rand.Reader, 1024)
	fmt.Println(privateKey)
}
