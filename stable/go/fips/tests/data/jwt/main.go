package main

import "fmt"

func main() {
	// Rule: jwt-pattern-00010
	rs256 := "jwt.SigningMethodRS256"
	fmt.Println(rs256)

	// Rule: jwt-pattern-00020
	ps256 := "jwt.SigningMethodPS256"
	fmt.Println(ps256)

	// Rule: jwt-pattern-00030
	es256 := "jwt.SigningMethodES256"
	fmt.Println(es256)

	// Rule: jwt-pattern-00040
	eddsa := "jwt.SigningMethodEdDSA"
	fmt.Println(eddsa)

	// Rule: jwt-pattern-00050
	hs256 := "jwt.SigningMethodHS256"
	fmt.Println(hs256)
}
