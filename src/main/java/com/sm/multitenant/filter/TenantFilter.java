/**
 * 
 */
package com.sm.multitenant.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import com.sm.multitenant.config.TenantAwareConnectionFactory;

import reactor.core.publisher.Mono;

/**
 * @author SHUBHAM Mhatre
 * @since JUNE-2024
 */
@Component
public class TenantFilter implements WebFilter{
	
	private final TenantAwareConnectionFactory tenantAwareConnectionFactory;
	public TenantFilter(TenantAwareConnectionFactory tenantAwareConnectionFactory) {
		this.tenantAwareConnectionFactory=tenantAwareConnectionFactory;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
		String accountId = extractTenantId(exchange);
		return tenantAwareConnectionFactory.fetchTenantDatabaseDetails(accountId)
			.flatMap(credentials -> {
				return tenantAwareConnectionFactory.createConnectionFactory(credentials.getUrl(), credentials.getUsername(), credentials.getPassword())
						.flatMap(factory -> chain.filter(exchange)
								.contextWrite(ctx -> ctx.put("tenantDbName", credentials.getDbName())
										.put("connectionFactory", factory)));
			});
	}

	/**
	 * @param exchange
	 * @return
	 */
	private String extractTenantId(ServerWebExchange exchange) {
		return exchange.getRequest().getHeaders().getFirst("tenant-id");
	}

}
