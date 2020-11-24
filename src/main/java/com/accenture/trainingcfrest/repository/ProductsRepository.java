package com.accenture.trainingcfrest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.accenture.trainingcfrest.domain.ProductsEntity;

@Repository
public interface ProductsRepository extends JpaRepository<ProductsEntity, String>{

	/**o <.., String> é a chave, se for chave composta tempos de meter a classe onde a definimos**/
	
	/** nas conversões de chave composta tb se tem de fazer a convesao das chaves:
	 *  map(item.getKey(), ProductsKeyTO.class)**/
	
	/** com este nome nao precisa de query, ele já sabe o que tem de fazer **/
	public List<ProductsEntity> findByNameOrderByManufacturerDesc(String name);
	
	@Query("SELECT t FROM ProductsEntity as t WHERE t.name like :keyword")
	public List<ProductsEntity> findByKeywordCustomQuery(@Param("keyword") String keyword);

	@Query("SELECT k FROM ProductsEntity k WHERE function('contains', k.name , :keyword, function('fuzzy', 0.5)) = function('contains_rhs') or function('contains', k.manufacturer , :keyword, function('fuzzy', 0.5)) = function('contains_rhs')")
	public List<ProductsEntity> findByKeywordFuzzyCustomQuery(@Param("keyword") String keyword);
}