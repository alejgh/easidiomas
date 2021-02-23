package com.easidiomas.api;

import com.easidiomas.api.filters.SecurityFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.servlet.Filter;

@SpringBootApplication
public class EasidiomasAPIGateway {

    public static void main(String... args) {
        SpringApplication.run(EasidiomasAPIGateway.class, args);
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
