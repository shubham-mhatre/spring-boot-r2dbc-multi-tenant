/**
 * 
 */
package com.sm.multitenant.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;

import io.r2dbc.spi.ConnectionFactory;

/**
 * @author MUM1436 : SHUBHAM Mhatre
 * @since FEB-2024
 */
@Configuration
public class R2dbcConfig extends AbstractR2dbcConfiguration{

	private final TenantAwareConnectionFactory tenantAwareConnectionFactory;
	
	public R2dbcConfig(TenantAwareConnectionFactory tenantAwareConnectionFactory) {
        this.tenantAwareConnectionFactory = tenantAwareConnectionFactory;
    }
	
	@Bean
	@Override
	public ConnectionFactory connectionFactory() {
		return new DelegatingConnectionFactory(tenantAwareConnectionFactory);
	}
	
}
