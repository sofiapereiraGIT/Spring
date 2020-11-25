package com.accenture.trainingcfrest.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accenture.trainingcfrest.domain.SalesOrderItemEntity;

@Repository
public interface SalesOrderItemRepository extends JpaRepository<SalesOrderItemEntity, String>{

	/**o <.., String> é a chave, se for chave composta tempos de meter a classe onde a definimos**/
	
	/** nas conversões de chave composta tb se tem de fazer a convesao das chaves:
	 *  map(item.getKey(), ProductsKeyTO.class)**/
	
	/** com este nome nao precisa de query, ele já sabe o que tem de fazer **/
	public List<SalesOrderItemEntity> findByStatus(String status);
}