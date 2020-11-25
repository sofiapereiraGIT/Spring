package com.accenture.trainingcfrest.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import com.accenture.trainingcfrest.service.SalesOrderService;
import com.accenture.trainingcfrest.dto.SalesOrderTO;
import java.util.*;

@RestController
@RequestMapping("/SalesOrder")
public class SalesOrderController {
	
	@Autowired
	SalesOrderService service;

	@GetMapping(value = "")
	public List<SalesOrderTO> getSalesOrder(){
		return service.findall();
	}
	
	/** mais variaveis seria "{id}/{outra}" e no Path seria "(..., @PathVariable(value = "outravar") String outravar)**/
	@GetMapping(value = "{id}")
	public SalesOrderTO getSalesOrderByID(@PathVariable(value = "id") String salesOrderID){
		return service.findById(salesOrderID);
	}
	
	@PostMapping(value = "")
	public SalesOrderTO postSalesOrder(@RequestBody SalesOrderTO salesOrder) {
		return service.saveSalesOrder(salesOrder);
	}
	
	@DeleteMapping(value = "{id}")
	public String deleteSalesOrder(@PathVariable(value = "id") String salesOrderID){
		return Boolean.toString(service.deleteSalesOrder(salesOrderID));
	}
	
	@PutMapping(value = "{id}")
	public SalesOrderTO updateSalesOrder(@PathVariable(value = "id") String salesOrderID, @RequestBody SalesOrderTO salesOrder){
		if(!salesOrderID.equals(salesOrder.getId())){
			return new SalesOrderTO();
		}
		
		return service.saveSalesOrder(salesOrder);
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
