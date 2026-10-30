package main

import "fmt"

func main() {
	// Rule: crypto-hygiene-pattern-00010
	var encryptionKey = []byte("my-secret-key-1234567890123456")
	var iv = []byte("1234567890123456")
	fmt.Println(encryptionKey, iv)

	// Rule: crypto-hygiene-pattern-00020
	var salt = []byte("static-salt-value")
	fmt.Println(salt)
}
