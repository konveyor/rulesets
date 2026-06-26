package main

import (
	"crypto/dsa"
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"fmt"
)

func main() {
	// Rule: key-strength-change-00010
	p224Key, _ := ecdsa.GenerateKey(elliptic.P224(), rand.Reader)
	fmt.Println(p224Key)

	// Rule: key-strength-change-00020
	var params dsa.Parameters
	dsa.GenerateParameters(&params, rand.Reader, dsa.L1024N160)
	dsaKey := new(dsa.PrivateKey)
	dsaKey.Parameters = params
	dsa.GenerateKey(dsaKey, rand.Reader)
	fmt.Println(dsaKey)
}
