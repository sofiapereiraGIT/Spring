package com.accenture.trainingcfrest.service;

import org.apache.logging.log4j.util.Strings;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.trainingcfrest.domain.ClientEntity;
import com.accenture.trainingcfrest.dto.ClientTO;
import com.accenture.trainingcfrest.repository.ClientRepository;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ClientService {
	
	@Autowired
	ClientRepository rep;
	
	@Autowired
	ModelMapper mapper;
	
	public List<ClientTO> findall(String keyword, boolean fuzzy) {
		List<ClientEntity> results = null;
		if(Strings.isEmpty(keyword)){
			results = rep.findAll();
		}
		else{
			if(fuzzy){
				results = rep.findByKeywordFuzzyCustomQuery(keyword);	
			}
			else{
				results = rep.findByKeywordCustomQuery("%"+keyword+"%");
			}
		}
		
		return results.stream().map(item -> {
			return mapper.map(item, ClientTO.class);
		}).collect(Collectors.toList());
	}
	
	public ClientTO findById(String clientID) {
		Optional<ClientEntity> found = rep.findById(clientID);
		if(found.isPresent()){
			return mapper.map(found.get(), ClientTO.class);
		}
		return null;
	}
	
	public ClientTO saveCli(ClientTO cli){
		ClientEntity save = rep.save(mapper.map(cli, ClientEntity.class));
		return mapper.map(save, ClientTO.class);
	}

	public Boolean deleteCli(String clientID){
		try {
			rep.deleteById(clientID);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
}
