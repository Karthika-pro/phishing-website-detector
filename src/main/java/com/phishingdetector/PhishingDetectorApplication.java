package com.phishingdetector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point of the application.
 *
 * Python comparison: this is the equivalent of your notebook's top-level cells
 * that ran in sequence — except here, nothing runs until this main() method
 * calls SpringApplication.run(). That call does a lot for us automatically:
 *   1. Starts an embedded web server (Tomcat) on port 8080
 *   2. Scans this package (and sub-packages) for classes annotated with
 *      @RestController, @Service, @Configuration, etc., and wires them together
 *   3. Serves anything in src/main/resources/static/ as static web content
 *
 * @SpringBootApplication is shorthand for three annotations combined:
 *   - @Configuration   (this class can define beans)
 *   - @EnableAutoConfiguration (Spring guesses sensible defaults from your dependencies)
 *   - @ComponentScan   (Spring looks for other annotated classes in this package tree)
 */
@SpringBootApplication
public class PhishingDetectorApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhishingDetectorApplication.class, args);
    }

}
