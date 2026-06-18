package main

import (
	"crypto/aes"
	"crypto/cipher"
	"fmt"
)

func main() {
	key := make([]byte, 32)
	iv := make([]byte, aes.BlockSize)
	plaintext := make([]byte, 32)
	ciphertext := make([]byte, 32)

	block, _ := aes.NewCipher(key)

	// Rule: cipher-modes-change-00010
	cbcEnc := cipher.NewCBCEncrypter(block, iv)
	cbcEnc.CryptBlocks(ciphertext, plaintext)

	cbcDec := cipher.NewCBCDecrypter(block, iv)
	cbcDec.CryptBlocks(plaintext, ciphertext)

	// Rule: cipher-modes-change-00020
	cfbEnc := cipher.NewCFBEncrypter(block, iv)
	cfbEnc.XORKeyStream(ciphertext, plaintext)

	cfbDec := cipher.NewCFBDecrypter(block, iv)
	cfbDec.XORKeyStream(plaintext, ciphertext)

	// Rule: cipher-modes-change-00030
	ofbStream := cipher.NewOFB(block, iv)
	ofbStream.XORKeyStream(ciphertext, plaintext)

	// Rule: cipher-modes-change-00040
	ctrStream := cipher.NewCTR(block, iv)
	ctrStream.XORKeyStream(ciphertext, plaintext)

	fmt.Println(ciphertext)
}
