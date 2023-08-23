package com.truongnq.springbootkeycloack.security;

import org.keycloak.adapters.KeycloakDeployment;
import org.keycloak.adapters.KeycloakDeploymentBuilder;
import org.keycloak.adapters.authorization.PolicyEnforcer;
import org.keycloak.representations.adapters.config.AdapterConfig;
import org.keycloak.representations.adapters.config.PolicyEnforcerConfig;

import java.util.concurrent.Callable;

/**
 * @author truongnq
 */
public class CustomKeycloakDeploymentBuilder extends KeycloakDeploymentBuilder {


    @Override
    protected KeycloakDeployment internalBuild(final AdapterConfig adapterConfig) {
        KeycloakDeployment deployment = super.internalBuild(adapterConfig);

        final PolicyEnforcerConfig policyEnforcerConfig = adapterConfig.getPolicyEnforcerConfig();

        if (policyEnforcerConfig != null) {
            deployment.setPolicyEnforcer(new Callable<>() {
                PolicyEnforcer policyEnforcer;
                @Override
                public PolicyEnforcer call() {
                    if (policyEnforcer == null) {
                        synchronized (deployment) {
                            if (policyEnforcer == null) {
                                PolicyEnforcer.Builder builder = PolicyEnforcer.builder()
                                        .authServerUrl(adapterConfig.getAuthServerUrl())
                                        .realm(adapterConfig.getRealm())
                                        .clientId(adapterConfig.getResource())
                                        .bearerOnly(adapterConfig.isBearerOnly())
                                        .credentialProvider(deployment.getClientAuthenticator())
                                        .enforcerConfig(policyEnforcerConfig)
                                        .httpClient(deployment.getClient());
                                policyEnforcer = new CacheablePolicyEnforcer(builder);
                            }
                        }
                    }
                    return policyEnforcer;
                }
            });
        }

        return deployment;
    }

    public static KeycloakDeployment build(AdapterConfig adapterConfig) {
        return new CustomKeycloakDeploymentBuilder().internalBuild(adapterConfig);
    }
}
