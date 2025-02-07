package org.konveyor.deps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.config.ConfigFileApplicationListener;

@SpringBootApplication
public class BootDepsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootDepsApplication.class, args);
	}

	public void method() {
		ConfigFileApplicationListener configFileApplicationListener = new ConfigFileApplicationListener();
	}
}
