package main

import "fmt"

func main() {
	// Rule: annotations-pattern-00090
	annotations1 := map[string]string{
		"nginx.ingress.kubernetes.io/auth-type":   "basic",
		"nginx.ingress.kubernetes.io/auth-secret": "basic-auth",
	}

	// Rule: annotations-pattern-00100
	annotations2 := map[string]string{
		"nginx.ingress.kubernetes.io/limit-rps": "10",
	}

	// Rule: annotations-pattern-00110
	annotations3 := map[string]string{
		"nginx.ingress.kubernetes.io/affinity":            "cookie",
		"nginx.ingress.kubernetes.io/session-cookie-name": "my-session",
	}

	// Rule: annotations-pattern-00120
	annotations4 := map[string]string{
		"nginx.ingress.kubernetes.io/whitelist-source-range": "10.0.0.0/8",
	}

	// Rule: annotations-pattern-00130
	annotations5 := map[string]string{
		"nginx.ingress.kubernetes.io/hsts":                    "true",
		"nginx.ingress.kubernetes.io/hsts-max-age":            "31536000",
		"nginx.ingress.kubernetes.io/hsts-include-subdomains": "true",
	}

	// Rule: annotations-pattern-00140
	annotations6 := map[string]string{
		"nginx.ingress.kubernetes.io/proxy-body-size": "10m",
	}

	fmt.Println(annotations1, annotations2, annotations3, annotations4, annotations5, annotations6)
}
