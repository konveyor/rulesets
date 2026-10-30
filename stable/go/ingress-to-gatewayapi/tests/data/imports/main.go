package main

import (
	// Rule: imports-change-00010
	networkingv1 "k8s.io/api/networking/v1"
)

func main() {
	ingress := &networkingv1.Ingress{}
	_ = ingress
}
