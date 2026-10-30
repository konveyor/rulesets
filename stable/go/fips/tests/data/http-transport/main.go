package main

import (
	"fmt"
	"net/http"
)

func main() {
	// Rule: http-transport-pattern-00010
	resp, err := http.Get("http://internal-service:8080/api/data")
	if err != nil {
		fmt.Println(err)
	}
	fmt.Println(resp.StatusCode)
}
