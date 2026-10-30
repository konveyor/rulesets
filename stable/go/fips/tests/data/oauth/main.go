package main

import (
	"context"
	"fmt"

	// Rule: oauth-change-00010
	"golang.org/x/oauth2"

	// Rule: oauth-change-00020
	oidc "github.com/coreos/go-oidc"
)

func main() {
	// Rule: oauth-change-00010
	config := &oauth2.Config{
		ClientID: "client-id",
	}
	fmt.Println("oauth2 config:", config)

	// Rule: oauth-change-00020
	ctx := context.Background()
	provider, _ := oidc.NewProvider(ctx, "https://issuer.example.com")
	verifier := provider.Verifier(&oidc.Config{ClientID: "client-id"})
	fmt.Println("oidc verifier:", verifier)
}
