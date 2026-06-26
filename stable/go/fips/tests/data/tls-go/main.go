package main

import (
	"crypto/tls"
	"fmt"
)

func main() {
	// Rule: tls-change-00010
	config1 := &tls.Config{
		MinVersion: tls.VersionTLS10,
		MaxVersion: tls.VersionTLS11,
	}
	fmt.Println(config1)

	// Rule: tls-change-00020
	config2 := &tls.Config{
		CipherSuites: []uint16{
			tls.TLS_RSA_WITH_RC4_128_SHA,
			tls.TLS_RSA_WITH_3DES_EDE_CBC_SHA,
		},
	}
	fmt.Println(config2)
}
