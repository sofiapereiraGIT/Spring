package com.accenture.trainingcfrest.service;

import org.apache.logging.log4j.util.Strings;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.trainingcfrest.domain.SalesOrderEntity;
import com.accenture.trainingcfrest.dto.SalesOrderTO;
import com.accenture.trainingcfrest.dto.ClientTO;
import com.accenture.trainingcfrest.dto.UserTO;
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
	SalesOrderItemRepository repItem;
	
	@Autowired
	ModelMapper mapper;
	
	public List<SalesOrderTO> findall() {
		List<SalesOrderEntity> results = rep.internalFindAll();		
		return results.stream().map(item -> {
			SalesOrderTO salesOrder = mapper.map(item, SalesOrderTO.class);
			
			List<SalesOrderItemTO> collect = item.getItems().stream().map(salesOrderItem -> {
				return mapper.map(salesOrderItem, SalesOrderItemTO.class);
			}).collect(Collectors.toList());
			salesOrder.setItems(collect);
			
			/*ClientTO cli = mapper.map(item.getClientID(), ClientTO.class);
			salesOrder.setClientID(cli);
			
			UserTO user = mapper.map(item.getUserID(), UserTO.class);
			salesOrder.setUserID(user);*/
			
			return salesOrder;
		}).collect(Collectors.toList());
	}
	
	public SalesOrderTO findById(String salesOrderID) {
		SalesOrderEntity found = rep.internalFindById(salesOrderID);
		if(found != null){
			SalesOrderTO salesOrder = mapper.map(found, SalesOrderTO.class);
			
			List<SalesOrderItemTO> collect = salesOrder.getItems().stream().map(salesOrderItem -> {
				return mapper.map(salesOrderItem, SalesOrderItemTO.class);
			}).collect(Collectors.toList());
			salesOrder.setItems(collect);
			
			/*ClientTO cli = mapper.map(found.get().getClientID(), ClientTO.class);
			salesOrder.setClientID(cli);
			
			UserTO user = mapper.map(found.get().getUserID(), UserTO.class);
			salesOrder.setUserID(user);*/
			
			return salesOrder;
		}
		return null;
	}
	
	public SalesOrderTO saveSalesOrder(SalesOrderTO so){		
		if (Strings.isEmpty(so.getId())) {
			so.setCreatedBY("spring");
			so.setCreatedAT((LocalDateTime.now().toString()));
		}
		so.setModifiedBY("spring");
		so.setModifiedAT(LocalDateTime.now().toString());
		
		SalesOrderEntity soEntity = mapper.map(so, SalesOrderEntity.class);
		SalesOrderEntity save = rep.save(soEntity);

		soEntity.getItems().stream().forEach(item -> item.setSalesOrderID(save));
		repItem.saveAll(soEntity.getItems());

		return mapper.map(save, SalesOrderTO.class);
	}

	public Boolean deleteSalesOrder(String salesOrderID){
		try {
			rep.deleteById(salesOrderID);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
}
