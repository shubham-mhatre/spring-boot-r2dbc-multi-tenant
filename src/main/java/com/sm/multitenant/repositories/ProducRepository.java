/**
 * 
 */
package com.sm.multitenant.repositories;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.sm.multitenant.models.Product;

/**
 * @author SHUBHAM Mhatre
 * @since JUNE-2024
 */
public interface ProducRepository extends R2dbcRepository<Product, Integer> {

}
