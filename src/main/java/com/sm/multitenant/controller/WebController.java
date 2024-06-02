/**
 * 
 */
package com.sm.multitenant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sm.multitenant.models.Product;
import com.sm.multitenant.service.ProductService;

import reactor.core.publisher.Flux;

/**
 * @author SHUBHAM Mhatre
 * @since JUNE-2024
 */

@RestController
public class WebController {
	
	
	private final ProductService productService;
	
	@Autowired
	public WebController(ProductService productService) {
		this.productService=productService;
	}
	
	@GetMapping("/productList")
	Flux<Product> getAllProducts(){
		return productService.getAllProductList();
	}

}
