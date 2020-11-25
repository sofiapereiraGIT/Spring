package com.accenture.trainingcfrest.service;

import org.apache.logging.log4j.util.Strings;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.trainingcfrest.domain.UserEntity;
import com.accenture.trainingcfrest.dto.UserTO;
import com.accenture.trainingcfrest.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {
	
	@Autowired
	UserRepository rep;
	
	@Autowired
	ModelMapper mapper;
	
	public List<UserTO> findall(String keyword, boolean fuzzy) {
		List<UserEntity> results = null;
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
			return mapper.map(item, UserTO.class);
		}).collect(Collectors.toList());
	}
	
	public UserTO findById(String userID) {
		Optional<UserEntity> found = rep.findById(userID);
		if(found.isPresent()){
			return mapper.map(found.get(), UserTO.class);
		}
		return null;
	}
	
	public UserTO saveUser(UserTO user){
		if (Strings.isEmpty(user.getId())) {
			user.setCreatedBY("spring");
			user.setCreatedAT((LocalDateTime.now().toString()));
		}
		user.setModifiedBY("spring");
		user.setModifiedAT(LocalDateTime.now().toString());
		
		UserEntity save = rep.save(mapper.map(user, UserEntity.class));
		return mapper.map(save, UserTO.class);
	}

	public Boolean deleteUser(String userID){
		try {
			rep.deleteById(userID);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
}
