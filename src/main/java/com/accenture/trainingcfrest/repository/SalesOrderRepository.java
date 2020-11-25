package com.accenture.trainingcfrest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.accenture.trainingcfrest.domain.SalesOrderEntity;

@Repository
public interface SalesOrderRepository extends JpaRepository<SalesOrderEntity, String>{

	/**o <.., String> é a chave, se for chave composta tempos de meter a classe onde a definimos**/
	
	/** nas conversões de chave composta tb se tem de fazer a convesao das chaves:
	 *  map(item.getKey(), ProductsKeyTO.class)**/
	
	/** com este nome nao precisa de query, ele já sabe o que tem de fazer **/
	public List<SalesOrderEntity> findByStatusOrderByCreatedATDesc(String status);
	
	@Query("SELECT k FROM SalesOrderEntity as k JOIN FETCH k.items as i")
	public List<SalesOrderEntity> internalFindAll();
	
	@Query("SELECT k FROM SalesOrderEntity as k LEFT JOIN FETCH k.items as i WHERE k.id = :id")
	public SalesOrderEntity internalFindById(@Param("id") String id);	
}