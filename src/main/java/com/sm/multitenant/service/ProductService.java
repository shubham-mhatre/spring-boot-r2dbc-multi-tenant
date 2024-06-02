/**
 * 
 */
package com.sm.multitenant.service;

import org.springframework.stereotype.Service;

import com.sm.multitenant.models.Product;
import com.sm.multitenant.repositories.ProducRepository;

import reactor.core.publisher.Flux;

/**
 * @author SHUBHAM Mhatre
 * @since June-2024
 */

@Service
public class ProductService {
	
	private final ProducRepository producRepository;
	
	public ProductService(ProducRepository producRepository) {
		this.producRepository=producRepository;
	}
	
	public Flux<Product> getAllProductList(){
		return producRepository.findAll();
	}

}
