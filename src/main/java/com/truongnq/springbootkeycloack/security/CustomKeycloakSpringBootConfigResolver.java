package com.truongnq.springbootkeycloack.security;

import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.KeycloakDeployment;
import org.keycloak.adapters.KeycloakDeploymentBuilder;
import org.keycloak.adapters.OIDCHttpFacade;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.representations.adapters.config.AdapterConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @author truongnq
 */
@Configuration
public class CustomKeycloakSpringBootConfigResolver implements KeycloakConfigResolver {
    private KeycloakDeployment keycloakDeployment;

    @Autowired(required=false)
    private AdapterConfig adapterConfig;

    @Override
    public KeycloakDeployment resolve(OIDCHttpFacade.Request request) {
        if (keycloakDeployment != null) {
            return keycloakDeployment;
        }

        keycloakDeployment = CustomKeycloakDeploymentBuilder.build(adapterConfig);

        return keycloakDeployment;
    }

    void setAdapterConfig(AdapterConfig adapterConfig) {
        this.adapterConfig = adapterConfig;
    }
}
