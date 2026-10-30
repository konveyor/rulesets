package main

import (
	"crypto/ecdsa"
	"crypto/ed25519"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/rsa"
	"fmt"
)

func main() {
	// Rule: pqc-change-00010
	rsaKey, _ := rsa.GenerateKey(rand.Reader, 2048)
	ciphertext, _ := rsa.EncryptPKCS1v15(rand.Reader, &rsaKey.PublicKey, []byte("hello"))
	fmt.Println(ciphertext)

	// Rule: pqc-change-00020
	ecdsaKey, _ := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
	hash := []byte("test-hash-value-for-signing-demo")
	signature, _ := ecdsa.SignASN1(rand.Reader, ecdsaKey, hash)
	fmt.Println(signature)

	// Rule: pqc-change-00030
	pubKey, privKey, _ := ed25519.GenerateKey(rand.Reader)
	sig := ed25519.Sign(privKey, []byte("message"))
	valid := ed25519.Verify(pubKey, []byte("message"), sig)
	fmt.Println(valid)
}
