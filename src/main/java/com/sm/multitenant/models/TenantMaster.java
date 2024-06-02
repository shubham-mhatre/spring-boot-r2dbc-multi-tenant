package com.sm.multitenant.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author SHUBHAM Mhatre
 * @since JUNE-2024
 */
@Table(name = "tenant_master")
@Data
@AllArgsConstructor
public class TenantMaster {
	
	@Id
	private Integer id;
	
	private String tenantId;
	private String dbName;
	private String url;
	private String username;
	private String password;
	private Integer isActive;

}
