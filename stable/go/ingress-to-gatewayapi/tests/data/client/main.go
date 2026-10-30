package main

import "fmt"

func main() {
	// Rule: client-pattern-00010
	// Simulates client-go Ingress API usage that must be migrated
	fmt.Println("clientset.NetworkingV1().Ingresses(namespace).List(ctx, opts)")

	// Rule: client-pattern-00020
	// Simulates client-go IngressClasses API usage that must be migrated
	fmt.Println("clientset.NetworkingV1().IngressClasses().Get(ctx, name, opts)")
}
