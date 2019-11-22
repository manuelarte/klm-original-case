package com.afkl.cases.df;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.io.IOException;

@SpringBootApplication
@EnableConfigurationProperties
public class Bootstrap {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(Bootstrap.class, args);
    }

}
