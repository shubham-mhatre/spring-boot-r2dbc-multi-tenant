package com.sm.multitenant.config;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.r2dbc.spi.Connection;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryMetadata;

/**
 * @author MUM1436 : SHUBHAM Mhatre
 * @since FEB-2024
 */
@Component
public class DelegatingConnectionFactory implements ConnectionFactory {

	private final TenantAwareConnectionFactory tenantAwareConnectionFactory;
	
	@Value("${spring.r2dbc.url}")
    private String masterUrl;

    @Value("${spring.r2dbc.username}")
    private String masterUsername;

    @Value("${spring.r2dbc.password}")
    private String masterPassword;

	public DelegatingConnectionFactory(TenantAwareConnectionFactory tenantAwareConnectionFactory) {
		this.tenantAwareConnectionFactory = tenantAwareConnectionFactory;
	}
	
	@Override
	public Publisher<? extends Connection> create() {
	    return tenantAwareConnectionFactory.getConnectionFactory()
	            .flatMapMany(ConnectionFactory::create);
	}

	@Override
	public ConnectionFactoryMetadata getMetadata() {
		return tenantAwareConnectionFactory.createConnectionFactory(masterUrl, masterUsername, masterPassword).block().getMetadata();
	}
}
