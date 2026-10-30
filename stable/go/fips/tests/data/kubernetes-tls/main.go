package main

import (
	"fmt"

	// Rule: kubernetes-tls-change-00010
	"k8s.io/client-go/rest"
)

func main() {
	// Rule: kubernetes-tls-change-00010
	config := &rest.Config{
		Host: "https://api-server:6443",
		TLSClientConfig: rest.TLSClientConfig{
			Insecure: true,
		},
	}
	fmt.Println("k8s config:", config)
}
