package main

import (
	"crypto/des"
	"crypto/rc4"
	"fmt"
)

func main() {
	// Rule: weak-cipher-change-00010
	desKey := make([]byte, 8)
	desBlock, _ := des.NewCipher(desKey)
	fmt.Println(desBlock)

	// Rule: weak-cipher-change-00020
	tripleDesKey := make([]byte, 24)
	tripleDesBlock, _ := des.NewTripleDESCipher(tripleDesKey)
	fmt.Println(tripleDesBlock)

	// Rule: weak-cipher-change-00030
	rc4Key := make([]byte, 16)
	rc4Cipher, _ := rc4.NewCipher(rc4Key)
	fmt.Println(rc4Cipher)
}
