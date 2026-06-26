package main

import (
	networkingv1 "k8s.io/api/networking/v1"
	metav1 "k8s.io/apimachinery/pkg/apis/meta/v1"
)

func main() {
	// Rule: core-types-change-00010
	ingress := &networkingv1.Ingress{
		ObjectMeta: metav1.ObjectMeta{Name: "test"},
	}
	_ = ingress

	// Rule: core-types-change-00020
	spec := networkingv1.IngressSpec{}
	_ = spec

	// Rule: core-types-change-00030
	rule := networkingv1.IngressRule{
		Host: "example.com",
	}
	_ = rule

	// Rule: core-types-change-00040
	ruleValue := networkingv1.IngressRuleValue{}
	_ = ruleValue

	// Rule: core-types-change-00050
	ingressClass := &networkingv1.IngressClass{
		ObjectMeta: metav1.ObjectMeta{Name: "nginx"},
	}
	_ = ingressClass

	// Rule: core-types-change-00060
	classSpec := networkingv1.IngressClassSpec{
		Controller: "k8s.io/ingress-nginx",
	}
	_ = classSpec

	// Rule: core-types-change-00070
	backend := networkingv1.IngressBackend{}
	_ = backend

	// Rule: core-types-change-00080
	serviceBackend := &networkingv1.IngressServiceBackend{
		Name: "my-service",
	}
	_ = serviceBackend
}
