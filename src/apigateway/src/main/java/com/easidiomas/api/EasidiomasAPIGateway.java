package com.easidiomas.api;

import com.easidiomas.api.filters.SecurityFilter;
import org.apache.catalina.connector.Connector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.servlet.Filter;
import javax.swing.*;
import java.util.Collections;

@SpringBootApplication
public class EasidiomasAPIGateway {

    private static final Logger LOGGER = LoggerFactory.getLogger(EasidiomasAPIGateway.class);
    public static final int SECURE_PORT = Integer.parseInt(System.getProperty("server.port", "8443"));
    public static final int PORT = Integer.parseInt(System.getProperty("server.http.port", "5000"));

    //HTTP port
    @Value("${server.http.port}")
    private int httpPort;

    public static void main(String... args) {
        LOGGER.info("Service starting on port [" + PORT + "] and port [" + SECURE_PORT + "]");
        SpringApplication.run(EasidiomasAPIGateway.class, args);
        //SpringApplication app = new SpringApplication(EasidiomasAPIGateway.class);
        //app.setDefaultProperties(Collections.singletonMap("server.port", PORT));
        //app.run(args);
        LOGGER.info("Service starting on port [" + PORT + "] and port [" + SECURE_PORT + "]");
    }

    /**
     * Crea el filtro de seguridad que asegura que cualquier petición a entrypoints lleva el token y el token es válido.
     *
     * @return el filtro de seguridad.
     */
    @Bean
    public FilterRegistrationBean securityFilterFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(securityFilter());
        registrationBean.addUrlPatterns("/api/users/*", "/api/statistics/*", "/api/translations/*", "/api/posts/*", "/api/chats/*", "/api/TextsToSpeechs/*");
        registrationBean.setName("securityFilter");
        registrationBean.setOrder(1);
        return registrationBean;
    }

    @Bean(name = "securityFilter")
    public Filter securityFilter() {
        return new SecurityFilter();
    }

    // Let's configure additional connector to enable support for both HTTP and HTTPS
    @Bean
    public ServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addAdditionalTomcatConnectors(createStandardConnector());
        return tomcat;
    }

    private Connector createStandardConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setPort(httpPort);
        return connector;
    }

}
