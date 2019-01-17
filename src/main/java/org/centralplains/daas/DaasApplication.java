package org.centralplains.daas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages = "org.centralplains.daas")
public class DaasApplication {

	public static void main(String[] args) {
		SpringApplication.run(DaasApplication.class, args);
	}

}

