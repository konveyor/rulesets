package org.konveyor.deps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.YamlJsonParser;

@SpringBootApplication
@EnableConfigurationProperties
public class BootDepsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootDepsApplication.class, args);
	}

	public void method() {
		DatabaseDriver gae = DatabaseDriver.GAE;
		System.out.println(gae);
		JsonParser parser = new YamlJsonParser();
		parser.parseMap("{\"hey\":\"hello\"}");
	}
}
