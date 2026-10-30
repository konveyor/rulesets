package main

import (
	"crypto/aes"
	"crypto/sha256"
	"fmt"
	"io"

	// Rule: crypto-change-00010
	"golang.org/x/crypto/hkdf"

	// Rule: crypto-change-00020
	"golang.org/x/crypto/sha3"

	// Rule: crypto-change-00030
	"golang.org/x/crypto/openpgp"

	// Rule: crypto-change-00040
	"golang.org/x/crypto/pkcs12"

	// Rule: crypto-change-00050
	"golang.org/x/crypto/acme"
	"golang.org/x/crypto/acme/autocert"

	// Rule: crypto-change-00060
	"golang.org/x/crypto/ocsp"

	// Rule: crypto-change-00070
	"golang.org/x/crypto/xts"
)

func main() {
	// Rule: crypto-change-00010
	secret := []byte("secret")
	salt := []byte("salt")
	info := []byte("info")
	reader := hkdf.New(sha256.New, secret, salt, info)
	key := make([]byte, 32)
	io.ReadFull(reader, key)
	fmt.Println("hkdf key:", key)

	// Rule: crypto-change-00020
	h := sha3.New256()
	h.Write([]byte("data"))
	hash := h.Sum(nil)
	fmt.Println("sha3 hash:", hash)

	// Rule: crypto-change-00030
	entities := openpgp.EntityList{}
	fmt.Println("openpgp entities:", len(entities))

	// Rule: crypto-change-00040
	pfxData := []byte{}
	_, _, err := pkcs12.Decode(pfxData, "password")
	fmt.Println("pkcs12 err:", err)

	// Rule: crypto-change-00050
	client := &acme.Client{}
	fmt.Println("acme client:", client)
	manager := &autocert.Manager{
		Prompt: autocert.AcceptTOS,
	}
	fmt.Println("autocert manager:", manager)

	// Rule: crypto-change-00060
	respBytes := []byte{}
	_, err = ocsp.ParseResponse(respBytes, nil)
	fmt.Println("ocsp err:", err)

	// Rule: crypto-change-00070
	aesKey := make([]byte, 64)
	_, err = xts.NewCipher(aes.NewCipher, aesKey)
	fmt.Println("xts err:", err)
}
