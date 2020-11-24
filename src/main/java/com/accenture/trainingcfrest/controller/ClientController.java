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
import com.accenture.trainingcfrest.service.ClientService;
import com.accenture.trainingcfrest.dto.ClientTO;
import java.util.*;

@RestController
@RequestMapping("/Client")
public class ClientController {
	
	@Autowired
	ClientService service;

	@GetMapping(value = "")
	public List<ClientTO> getClients(@RequestParam(value = "keyword", required=false) String keyword, @RequestParam(value = "fuzzy", required=false) boolean fuzzy){
		return service.findall(keyword, fuzzy);
	}
	
	/** mais variaveis seria "{id}/{outra}" e no Path seria "(..., @PathVariable(value = "outravar") String outravar)**/
	@GetMapping(value = "{id}")
	public ClientTO getClientByID(@PathVariable(value = "id") String clientID){
		return service.findById(clientID);
	}
	
	@PostMapping(value = "")
	public ClientTO postClient(@RequestBody ClientTO cli) {
		return service.saveCli(cli);
	}
	
	@DeleteMapping(value = "{id}")
	public String deleteClient(@PathVariable(value = "id") String clientID){
		return Boolean.toString(service.deleteCli(clientID));
	}
	
	@PutMapping(value = "{id}")
	public ClientTO updateClient(@PathVariable(value = "id") String clientID, @RequestBody ClientTO cli){
		if(!clientID.equals(cli.getId())){
			return new ClientTO();
		}
		
		return service.saveCli(cli);
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
