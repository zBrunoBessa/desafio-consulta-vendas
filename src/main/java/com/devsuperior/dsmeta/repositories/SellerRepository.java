package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsmeta.entities.Seller;
import com.devsuperior.dsmeta.projections.SellerProjection;

public interface SellerRepository extends JpaRepository<Seller, Long> {
	
	@Query(nativeQuery = true, value="SELECT DISTINCT(tb_seller.name), SUM(tb_sales.amount) AS total "
			+ "FROM tb_seller "
			+ "INNER JOIN tb_sales ON tb_sales.seller_id = tb_seller.id "
			+ "WHERE tb_sales.date BETWEEN :dateMin AND :dateMax "
			+ "GROUP BY tb_seller.name "
			+ "ORDER BY tb_seller.name ASC;")
	List<SellerProjection> search1(LocalDate dateMin, LocalDate dateMax);
}
