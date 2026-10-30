package main

import (
	// Rule: ssh-change-00010
	"golang.org/x/crypto/ssh"
)

func main() {
	// Rule: ssh-change-00010
	config := &ssh.ClientConfig{
		User: "admin",
		Auth: []ssh.AuthMethod{ssh.Password("password")},
		// Rule: ssh-change-00020
		HostKeyCallback: ssh.InsecureIgnoreHostKey(),
	}
	_ = config
}
