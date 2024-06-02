package com.sm.multitenant.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.sm.multitenant.models.TenantMaster;
import com.sm.multitenant.repositories.TenantMasterRepository;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * Author: SHUBHAM Mhatre
 * Since: FEB-2024
 */
@Slf4j
@Component
public class TenantAwareConnectionFactory {


	private final TenantMasterRepository tenantMasterRepository;
	
    @Autowired
    public TenantAwareConnectionFactory(@Lazy TenantMasterRepository tenantMasterRepository) {
       this.tenantMasterRepository=tenantMasterRepository;
    }

    @Value("${spring.r2dbc.url}")
    private String masterUrl;

    @Value("${spring.r2dbc.username}")
    private String masterUsername;

    @Value("${spring.r2dbc.password}")
    private String masterPassword;

    public Mono<ConnectionFactory> getConnectionFactory() {
        return Mono.deferContextual(ctx -> {
            if (ctx.hasKey("connectionFactory")) {
                return Mono.just((ConnectionFactory) ctx.get("connectionFactory"));
            }

            String tenantDbName = ctx.getOrDefault("tenantDbName", null);
            log.info("inside getConnectionFactory :: tenantDbName :: {}", tenantDbName);
            if (tenantDbName == null) {
                log.info("Using master database connection");
                return createConnectionFactory(masterUrl, masterUsername, masterPassword);
            } else {
                return fetchTenantDatabaseDetails(tenantDbName)
                        .flatMap(credentials -> {
                            log.info("Setting datasource for tenant: {}", credentials);
                            return createConnectionFactory(credentials.getUrl(), credentials.getUsername(), credentials.getPassword());
                        });
            }
        });
    }

    public Mono<TenantMaster> fetchTenantDatabaseDetails(String tenantId) {
        log.info("inside fetchTenantDatabaseDetails() :: tenantId :: {} ", tenantId);
        // Fetch tenant database details from the master database
        // This is a stub implementation. Replace with actual database query.

        return tenantMasterRepository.findByTenantIdAndIsActive(tenantId, 1);
//        return Mono.just(new TenantMaster(1,"tenant_1","tenant_1","r2dbc:mysql://localhost:3306/tenant_1",
//        		"root","root",1));
    }


    public Mono<ConnectionFactory> createConnectionFactory(String url, String username, String password) {
        log.info("************ inside createConnectionFactory :: URL : {} : username : {} : password : {} ", url, username, password);
        ConnectionFactory factory = ConnectionFactories.get(ConnectionFactoryOptions.parse(url)
            .mutate()
            .option(ConnectionFactoryOptions.USER, username)
            .option(ConnectionFactoryOptions.PASSWORD, password)
            .build());
        return Mono.just(factory);
    }
}
