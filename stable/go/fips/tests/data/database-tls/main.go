package main

import "fmt"

func main() {
	// Rule: database-tls-pattern-00010
	pgDSN := "host=db.example.com user=app dbname=mydb sslmode=disable"
	fmt.Println(pgDSN)

	// Rule: database-tls-pattern-00020
	mysqlDSN := "user:pass@tcp(db.example.com:3306)/mydb?tls=false"
	fmt.Println(mysqlDSN)
}
