package main

import "fmt"

func main() {
	// Rule: annotations-pattern-00010
	annotations1 := map[string]string{
		"nginx.ingress.kubernetes.io/rewrite-target": "/$1",
		"nginx.ingress.kubernetes.io/use-regex":      "true",
	}

	// Rule: annotations-pattern-00020
	annotations2 := map[string]string{
		"nginx.ingress.kubernetes.io/ssl-redirect":       "true",
		"nginx.ingress.kubernetes.io/force-ssl-redirect": "true",
	}

	// Rule: annotations-pattern-00030
	annotations3 := map[string]string{
		"nginx.ingress.kubernetes.io/configuration-snippet": "more_set_headers X-Frame-Options: DENY",
		"nginx.ingress.kubernetes.io/server-snippet":        "listen 8080;",
	}

	// Rule: annotations-pattern-00040
	annotations4 := map[string]string{
		"nginx.ingress.kubernetes.io/canary":        "true",
		"nginx.ingress.kubernetes.io/canary-weight": "20",
	}

	// Rule: annotations-pattern-00050
	annotations5 := map[string]string{
		"nginx.ingress.kubernetes.io/proxy-read-timeout":    "60",
		"nginx.ingress.kubernetes.io/proxy-send-timeout":    "60",
		"nginx.ingress.kubernetes.io/proxy-connect-timeout": "10",
	}

	// Rule: annotations-pattern-00060
	annotations6 := map[string]string{
		"kubernetes.io/ingress.class": "nginx",
	}

	// Rule: annotations-pattern-00070
	annotations7 := map[string]string{
		"controller": "k8s.io/ingress-nginx",
	}

	// Rule: annotations-pattern-00080
	annotations8 := map[string]string{
		"nginx.ingress.kubernetes.io/enable-cors":        "true",
		"nginx.ingress.kubernetes.io/cors-allow-origin":  "https://example.com",
		"nginx.ingress.kubernetes.io/cors-allow-methods": "GET,POST",
	}

	fmt.Println(annotations1, annotations2, annotations3, annotations4, annotations5, annotations6, annotations7, annotations8)
}
