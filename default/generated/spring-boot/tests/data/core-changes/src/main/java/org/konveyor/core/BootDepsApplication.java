package org.konveyor.deps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DatabaseDriver;

@SpringBootApplication
@EnableConfigurationProperties
public class BootDepsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootDepsApplication.class, args);
	}

	public void method() {
		DatabaseDriver gae = DatabaseDriver.GAE;
		System.out.println(gae);
	}
}
