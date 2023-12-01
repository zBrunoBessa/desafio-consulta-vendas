package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SaleProjection;

public interface SaleRepository extends JpaRepository<Sale, Long> {
	
	@Query(nativeQuery = true, value = "SELECT tb_sales.id, tb_sales.date, tb_sales.amount, tb_seller.name AS sellerName "
	        + " FROM tb_sales "
	        + " LEFT JOIN tb_seller ON tb_sales.seller_id = tb_seller.id "
	        + " WHERE UPPER(tb_seller.name) LIKE UPPER(CONCAT('%',:name,'%')) AND tb_sales.date BETWEEN :min AND :max "
	        + " ORDER BY tb_sales.id")
	List<SaleProjection> search1(LocalDate min, LocalDate max, String name);
}
