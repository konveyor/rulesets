package main

import (
	networkingv1 "k8s.io/api/networking/v1"
)

func main() {
	// Rule: core-types-change-00090
	port := networkingv1.ServiceBackendPort{Number: 8080}
	_ = port

	// Rule: core-types-change-00100
	path := networkingv1.HTTPIngressPath{
		Path: "/api",
	}
	_ = path

	// Rule: core-types-change-00110
	httpRuleValue := &networkingv1.HTTPIngressRuleValue{
		Paths: []networkingv1.HTTPIngressPath{
			{Path: "/"},
		},
	}
	_ = httpRuleValue
}
