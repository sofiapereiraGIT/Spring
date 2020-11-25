package com.accenture.trainingcfrest.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import com.accenture.trainingcfrest.service.ProductsService;
import com.sap.cloud.security.xsuaa.token.Token;
import com.accenture.trainingcfrest.dto.ProductsTO;
import java.util.*;

@RestController
@RequestMapping("/Products")
public class ProductsController {
	
	//@AuthenticationPrincipal Token token
	
	@Autowired
	ProductsService service;

	@GetMapping(value = "")
	public List<ProductsTO> getProducts(@RequestParam(value = "keyword", required=false) String keyword, @RequestParam(value = "fuzzy", required=false) boolean fuzzy){
		return service.findall(keyword, fuzzy);
	}
	
	/** mais variaveis seria "{id}/{outra}" e no Path seria "(..., @PathVariable(value = "outravar") String outravar)**/
	@GetMapping(value = "{id}")
	public ProductsTO getProductByID(@PathVariable(value = "id") String productID){
		return service.findById(productID);
	}
	
	@PostMapping(value = "")
	public ProductsTO postProduct(@RequestBody ProductsTO product) {
		return service.saveProduct(product);
	}
	
	@DeleteMapping(value = "{id}")
	public String deleteProduct(@PathVariable(value = "id") String productID){
		return Boolean.toString(service.deleteProduct(productID));
	}
	
	@PutMapping(value = "{id}")
	public ProductsTO updateProduct(@PathVariable(value = "id") String productID, @RequestBody ProductsTO product){
		if(!productID.equals(product.getId())){
			return new ProductsTO();
		}
		
		return service.saveProduct(product);
	}
	
	/** Hellos **/
	
	@GetMapping(value = "/hello")
	public String helloGet(){
		return "Hello GET";
	}
	
	@PostMapping(value = "/hello")
	public String helloPost(){
		return "Hello POST";
	}

	@PutMapping(value = "/hello")
	public String helloPut(){
		return "Hello PUT";
	}
	
	@DeleteMapping(value = "/hello")
	public String helloDelete(){
		return "Hello DELETE";
	}
}
