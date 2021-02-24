package com.easidiomas.api;

import com.easidiomas.api.filters.SecurityFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.servlet.Filter;
import java.util.Collections;

@SpringBootApplication
public class EasidiomasAPIGateway {

    private static final Logger LOGGER = LoggerFactory.getLogger(EasidiomasAPIGateway.class);
    private static final int PORT = Integer.parseInt(System.getenv("SERVER_PORT")!=null ? System.getenv("SERVER_PORT"): "5001");

    public static void main(String... args) {
        LOGGER.info("Service starting on port " + PORT);
        SpringApplication app = new SpringApplication(EasidiomasAPIGateway.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", PORT));
        app.run(args);
        LOGGER.info("Service started on port " + PORT);
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
        registrationBean.addUrlPatterns("/api/users/*", "/api/statistics/*", "/api/translations/*");
        registrationBean.setName("securityFilter");
        registrationBean.setOrder(1);
        return registrationBean;
    }

    @Bean(name = "securityFilter")
    public Filter securityFilter() {
        return new SecurityFilter();
    }

}
