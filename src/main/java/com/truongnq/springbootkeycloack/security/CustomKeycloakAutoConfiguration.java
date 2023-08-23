package com.truongnq.springbootkeycloack.security;

import org.keycloak.adapters.springboot.KeycloakAutoConfiguration;
import org.keycloak.adapters.springboot.KeycloakSpringBootProperties;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author truongnq
 */
@Configuration
@ConditionalOnWebApplication
@EnableAutoConfiguration(exclude = KeycloakAutoConfiguration.class)
@EnableConfigurationProperties(KeycloakSpringBootProperties.class)
public class CustomKeycloakAutoConfiguration extends KeycloakAutoConfiguration {
    @Bean
    @Override
    public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> getKeycloakContainerCustomizer() {
        // Using springSecurityFilterChain to configure Keycloak authentication instead of Tomcat's container
        // This is to fix the issue of Keycloak's PolicyEnforcer calling Keycloak twice for each request
        return factory -> {};
    }
}
