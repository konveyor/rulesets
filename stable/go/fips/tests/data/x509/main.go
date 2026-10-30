package main

import (
	"crypto/rand"
	// Rule: x509-change-00020
	"crypto/tls"
	// Rule: x509-change-00010
	"crypto/x509"
	"crypto/x509/pkix"
	"math/big"
	"time"
)

func main() {
	// Rule: x509-change-00010
	template := &x509.Certificate{
		SerialNumber: big.NewInt(1),
		Subject:      pkix.Name{Organization: []string{"Test"}},
		NotBefore:    time.Now(),
		NotAfter:     time.Now().Add(365 * 24 * time.Hour),
	}
	certDER, _ := x509.CreateCertificate(rand.Reader, template, template, nil, nil)

	// Rule: x509-change-00010
	_, _ = x509.ParseCertificate(certDER)

	// Rule: x509-change-00020
	_, _ = tls.LoadX509KeyPair("cert.pem", "key.pem")
}
