package com.accenture.trainingcfrest.service;

import org.apache.logging.log4j.util.Strings;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.trainingcfrest.domain.ProductsEntity;
import com.accenture.trainingcfrest.dto.ProductsTO;
import com.accenture.trainingcfrest.repository.ProductsRepository;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductsService {
	
	@Autowired
	ProductsRepository rep;
	
	@Autowired
	ModelMapper mapper;
	
	public List<ProductsTO> findall(String keyword, boolean fuzzy) {
		List<ProductsEntity> results = null;
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
			return mapper.map(item, ProductsTO.class);
		}).collect(Collectors.toList());
	}
	
	public ProductsTO findById(String productID) {
		Optional<ProductsEntity> found = rep.findById(productID);
		if(found.isPresent()){
			return mapper.map(found.get(), ProductsTO.class);
		}
		return null;
	}
	
	public ProductsTO saveProduct(ProductsTO product){
		ProductsEntity save = rep.save(mapper.map(product, ProductsEntity.class));
		return mapper.map(save, ProductsTO.class);
	}

	public Boolean deleteProduct(String productID){
		try {
			rep.deleteById(productID);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
}
