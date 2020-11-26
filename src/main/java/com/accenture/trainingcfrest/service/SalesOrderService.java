package com.accenture.trainingcfrest.service;

import org.apache.logging.log4j.util.Strings;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.trainingcfrest.domain.SalesOrderEntity;
import com.accenture.trainingcfrest.dto.SalesOrderTO;
import com.accenture.trainingcfrest.dto.SalesOrderItemTO;
import com.accenture.trainingcfrest.repository.SalesOrderItemRepository;
import com.accenture.trainingcfrest.repository.SalesOrderRepository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SalesOrderService {
	
	@Autowired
	SalesOrderRepository rep;
	
	@Autowired
	SalesOrderItemRepository repItem;
	
	@Autowired
	ModelMapper mapper;
	
	public List<SalesOrderTO> findAll(){
		List<SalesOrderEntity> result = rep.findAll();
		
		return result.stream().map(item -> {
			SalesOrderTO salesOrder = mapper.map(item, SalesOrderTO.class);
			return salesOrder;
		}).collect(Collectors.toList());
	}
	
	public SalesOrderTO findById(String salesOrderID){
		SalesOrderEntity found = rep.internalFindById(salesOrderID);
		if (found!=null){
			SalesOrderTO salesOrder = mapper.map(found, SalesOrderTO.class);
			
			/*List<SalesOrderItemTO> collect = salesOrder.getItems().stream().map(salesOrderItem -> {
				return mapper.map(salesOrderItem, SalesOrderItemTO.class);
			}).collect(Collectors.toList());
			salesOrder.setItems(collect);*/
			
			return salesOrder;
		}
		return null;
	}
	
	public SalesOrderTO saveSalesOrder(SalesOrderTO salesOrder){	
		SalesOrderEntity salesOrderEntity = mapper.map(salesOrder, SalesOrderEntity.class);
		
		if (Strings.isEmpty(salesOrderEntity.getId())){
			salesOrderEntity.setCreatedBY("spring");
			salesOrderEntity.setCreatedAT(LocalDateTime.now());
		}
		salesOrderEntity.setModifiedBY("spring");
		salesOrderEntity.setModifiedAT(LocalDateTime.now());
		
		SalesOrderEntity savedEntity = rep.save(salesOrderEntity);
		
		salesOrderEntity.getItems().stream().forEach(item -> {
            item.setSalesOrderID(savedEntity);
            
            if (Strings.isEmpty(salesOrderEntity.getId())){
    			item.setCreatedBY("spring");
    			item.setCreatedAT(LocalDateTime.now());
    		}
            item.setModifiedBY("spring");
            item.setModifiedAT(LocalDateTime.now());
            
            repItem.save(item);
        });
		
		return mapper.map(savedEntity, SalesOrderTO.class);
	}

	public Boolean deleteSalesOrder(String salesOrderID){
		try {
			//tem de se remover os items tb	
			SalesOrderEntity found = rep.internalFindById(salesOrderID);
			found.getItems().stream().forEach(item -> {
				repItem.delete(item);
			});
			
			rep.deleteById(salesOrderID);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
}
