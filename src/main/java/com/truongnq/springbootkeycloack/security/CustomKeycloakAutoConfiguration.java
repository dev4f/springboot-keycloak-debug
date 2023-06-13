//package com.mozen.springbootkeycloack.security;
//
//import org.keycloak.adapters.springboot.KeycloakAutoConfiguration;
//import org.keycloak.adapters.springboot.KeycloakSpringBootProperties;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.boot.web.server.WebServerFactoryCustomizer;
//import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//
///**
// * @author truongnq
// */
//@Configuration
//@ConditionalOnWebApplication
//@EnableConfigurationProperties(KeycloakSpringBootProperties.class)
//public class Custom extends KeycloakAutoConfiguration {
//
//    @Bean
//    @Override
//    public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> getKeycloakContainerCustomizer() {
//        return new WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>() {
//            @Override
//            public void customize(ConfigurableServletWebServerFactory factory) {
//                System.out.println("Customize");
//            }
//        };
//    }
//}
