package com.penta.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PentaBdApplication {

	public static void main(String[] args) {
//		  String dir = System.getProperty("user.dir");

		  // directory from where the program was launched
		  // e.g /home/mkyong/projects/core-java/java-io
//		  System.out.println(dir);
		SpringApplication.run(PentaBdApplication.class, args);
		
	}

}
 