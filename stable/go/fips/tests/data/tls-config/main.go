package main

import (
	"crypto/tls"
	"fmt"
)

func main() {
	// Rule: tls-config-pattern-00010
	config := &tls.Config{
		ServerName: "",
	}
	fmt.Println(config)
}
