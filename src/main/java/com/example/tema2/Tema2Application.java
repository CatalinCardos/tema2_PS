package com.example.tema2;

import com.example.tema2.Repo.UserREPO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserREPO.class)
public class Tema2Application {

    public static void main(String[] args) {
        SpringApplication.run(Tema2Application.class, args);
    }

}
