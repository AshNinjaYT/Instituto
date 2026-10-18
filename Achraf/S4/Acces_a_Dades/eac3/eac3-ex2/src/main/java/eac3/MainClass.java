/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eac3;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 *
 * @author professor
 */
@SpringBootApplication
@Configuration
@EnableJpaRepositories
public class MainClass implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(MainClass.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}
