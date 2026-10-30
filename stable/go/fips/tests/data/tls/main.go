package main

import (
	"crypto/tls"
	"crypto/x509"
	"fmt"
)

func main() {
	// Rule: tls-pattern-00010
	config := &tls.Config{
		VerifyPeerCertificate: func(rawCerts [][]byte, verifiedChains [][]*x509.Certificate) error {
			return nil
		},
		VerifyConnection: func(cs tls.ConnectionState) error {
			return nil
		},
	}
	fmt.Println(config)

	// Rule: tls-pattern-00020
	config2 := &tls.Config{
		CurvePreferences: []tls.CurveID{tls.X25519, tls.CurveP256},
	}
	fmt.Println(config2)
}
