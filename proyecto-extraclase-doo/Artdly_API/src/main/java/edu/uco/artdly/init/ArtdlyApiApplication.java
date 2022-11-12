package edu.uco.artdly.init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"edu.uco.artdly"})
public class ArtdlyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArtdlyApiApplication.class, args);
	}

}
