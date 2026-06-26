package main

import (
	"crypto/aes"
	"crypto/cipher"
	"crypto/hmac"
	"crypto/sha256"
	"fmt"
	// Rule: crypto-hygiene-change-00010
	"math/rand"

	// Rule: crypto-hygiene-change-00020
	"golang.org/x/crypto/pbkdf2"
)

func main() {
	// Rule: crypto-hygiene-change-00010
	key := make([]byte, 32)
	for i := range key {
		key[i] = byte(rand.Intn(256))
	}

	// Rule: crypto-hygiene-change-00020
	password := []byte("password")
	salt := []byte("somesaltvalue123")
	hash := pbkdf2.Key(password, salt, 10000, 32, sha256.New)
	fmt.Println(hash)

	// Rule: crypto-hygiene-change-00030
	mac := hmac.New(sha256.New, key)
	mac.Write([]byte("message"))
	fmt.Println(mac.Sum(nil))

	// Rule: crypto-hygiene-change-00040
	block, _ := aes.NewCipher(key)
	gcm, _ := cipher.NewGCM(block)
	nonce := make([]byte, gcm.NonceSize())
	ciphertext := gcm.Seal(nil, nonce, []byte("plaintext"), nil)
	fmt.Println(ciphertext)
}
