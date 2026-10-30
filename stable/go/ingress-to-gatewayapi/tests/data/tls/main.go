package main

import (
	networkingv1 "k8s.io/api/networking/v1"
)

func main() {
	// Rule: tls-change-00010
	tls := networkingv1.IngressTLS{
		Hosts:      []string{"example.com"},
		SecretName: "example-com-tls",
	}
	_ = tls
}
