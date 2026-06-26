package main

import (
	"crypto/tls"
	"fmt"

	// Rule: grpc-tls-change-00010
	// Rule: grpc-tls-change-00030
	"google.golang.org/grpc"

	// Rule: grpc-tls-change-00020
	"google.golang.org/grpc/credentials/insecure"

	// Rule: grpc-tls-change-00030
	"google.golang.org/grpc/credentials"
)

func main() {
	// Rule: grpc-tls-change-00010
	conn1, _ := grpc.Dial("service:443", grpc.WithInsecure())
	fmt.Println("insecure conn:", conn1)

	// Rule: grpc-tls-change-00020
	conn2, _ := grpc.Dial("service:443", grpc.WithTransportCredentials(insecure.NewCredentials()))
	fmt.Println("insecure creds conn:", conn2)

	// Rule: grpc-tls-change-00030
	creds := credentials.NewTLS(&tls.Config{
		MinVersion: tls.VersionTLS12,
		MaxVersion: tls.VersionTLS12,
	})
	conn3, _ := grpc.Dial("service:443", grpc.WithTransportCredentials(creds))
	fmt.Println("tls conn:", conn3)
}
