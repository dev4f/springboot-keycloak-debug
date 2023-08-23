package com.truongnq.springbootkeycloack.security;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.AuthorizationContext;
import org.keycloak.adapters.authorization.PolicyEnforcer;
import org.keycloak.adapters.authorization.spi.HttpRequest;
import org.keycloak.adapters.authorization.spi.HttpResponse;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import static java.util.Objects.nonNull;

/**
 * @author truongnq
 */
@Slf4j
public class CacheablePolicyEnforcer extends PolicyEnforcer {

    private final Cache<String, AuthorizationContext> contexts = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build();
    protected CacheablePolicyEnforcer(Builder builder) {
        super(builder);
    }

    @Override
    public AuthorizationContext enforce(HttpRequest request, HttpResponse response) {

        String name = request.getPrincipal().getName();
        String path = request.getRelativePath();
        String method = request.getMethod();
        String key = name + path + method;
        log.info("Key: {}", key);

        AuthorizationContext cached = contexts.getIfPresent(key);
        if (nonNull(cached)) {
            log.info("Cached: {}", cached);
            return cached;
        }
        AuthorizationContext enforced = super.enforce(request, response);
        if (nonNull(enforced)) contexts.put(key, enforced);
        return enforced;
    }


}
