package main

import (
	"crypto/md5"
	"crypto/sha1"
	"fmt"
)

func main() {
	data := []byte("test data")

	// Rule: weak-hash-change-00010
	md5Hash := md5.New()
	md5Hash.Write(data)
	fmt.Println(md5Hash.Sum(nil))

	// Rule: weak-hash-change-00020
	sha1Hash := sha1.New()
	sha1Hash.Write(data)
	fmt.Println(sha1Hash.Sum(nil))
}
