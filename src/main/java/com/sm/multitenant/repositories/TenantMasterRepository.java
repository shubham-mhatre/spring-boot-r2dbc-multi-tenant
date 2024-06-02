package com.sm.multitenant.repositories;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.sm.multitenant.models.TenantMaster;

import reactor.core.publisher.Mono;

/**
 * @author SHUBHAM Mhatre
 * @since JUNE-2024
 */

@Repository
public interface TenantMasterRepository extends R2dbcRepository<TenantMaster, Integer> {

	Mono<TenantMaster> findByTenantIdAndIsActive(String tenantId,Integer isActive);
}
