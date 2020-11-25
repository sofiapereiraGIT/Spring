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

import com.accenture.trainingcfrest.service.UserService;
import com.sap.cloud.security.xsuaa.token.Token;
import com.accenture.trainingcfrest.dto.UserTO;
import java.util.*;

@RestController
@RequestMapping("/User")
public class UserController {
	
	//@AuthenticationPrincipal Token token, 
	
	@Autowired
	UserService service;

	@GetMapping(value = "")
	public List<UserTO> getUsers(@RequestParam(value = "keyword", required=false) String keyword, @RequestParam(value = "fuzzy", required=false) boolean fuzzy){
		return service.findall(keyword, fuzzy);
	}
	
	/** mais variaveis seria "{id}/{outra}" e no Path seria "(..., @PathVariable(value = "outravar") String outravar)**/
	@GetMapping(value = "{id}")
	public UserTO getUserByID(@PathVariable(value = "id") String userID){
		return service.findById(userID);
	}
	
	@PostMapping(value = "")
	public UserTO postClient(@RequestBody UserTO user) {
		return service.saveUser(user);
	}
	
	@DeleteMapping(value = "{id}")
	public String deleteUser(@PathVariable(value = "id") String userID){
		return Boolean.toString(service.deleteUser(userID));
	}
	
	@PutMapping(value = "{id}")
	public UserTO updateClient(@PathVariable(value = "id") String userID, @RequestBody UserTO user){
		if(!userID.equals(user.getId())){
			return new UserTO();
		}
		
		return service.saveUser(user);
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
