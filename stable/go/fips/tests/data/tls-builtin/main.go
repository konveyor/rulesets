package main

import (
	"crypto/tls"
	"fmt"
)

func main() {
	// Rule: tls-pattern-00010
	config := &tls.Config{
		InsecureSkipVerify: true,
	}
	fmt.Println(config)
}
