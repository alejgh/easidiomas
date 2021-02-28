package com.easidiomas.chatservice;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.easidiomas.chatservice.controller.ChatsController;

@SpringBootApplication
public class ChatsApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(ChatsController.class);
	private static final int PORT = Integer
			.parseInt(System.getenv("SERVER_PORT") != null ? System.getenv("SERVER_PORT") : "5000");

	public static void main(String[] args) {
		LOGGER.info("ChatService starting on port " + PORT);
		SpringApplication app = new SpringApplication(ChatsApplication.class);
		app.setDefaultProperties(Collections.singletonMap("server.port", PORT));
		app.run(args);
		LOGGER.info("ChatService started on port " + PORT);
	}

}
