package br.com.dti.biblioteca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="br.com.dti.biblioteca")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
