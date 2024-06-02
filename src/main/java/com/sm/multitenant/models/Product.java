/**
 * 
 */
package com.sm.multitenant.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

/**
 * @author SHUBHAM Mhatre
 * @since JUNE-2024
 */
@Data
@Table(name="product")
public class Product {

	@Id
	private Integer id;
	private String productName;
	private Integer quantity;
	private Double taxRate;
	private Double totalPrice;
}
