package com.easidiomas.usersservice;

import com.easidiomas.usersservice.model.User;
import com.easidiomas.usersservice.persistence.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

import java.util.Collections;

@SpringBootApplication
@EnableNeo4jRepositories
public class Service {

    private static final int PORT = Integer.parseInt(System.getenv("SERVER_PORT")!=null ? System.getenv("SERVER_PORT"): "5000");
    private static final Logger LOGGER = LoggerFactory.getLogger(Service.class);


    public static void main(String... args) {
        LOGGER.info("Service starting on port " + PORT);
        SpringApplication app = new SpringApplication(Service.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", PORT));
        app.run(args);
        LOGGER.info("Service started on port " + PORT);
    }
}
