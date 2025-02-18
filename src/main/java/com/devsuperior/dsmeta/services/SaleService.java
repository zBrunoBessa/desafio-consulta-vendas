package com.devsuperior.dsmeta.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SaleProjection;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}
	
	public List<SaleProjection> search2(String minDate, String maxDate, String name){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate max;
		LocalDate min;
		try {
            max = (maxDate != null && !maxDate.isEmpty()) ? LocalDate.parse(maxDate, formatter) : LocalDate.now();
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            max = LocalDate.now();
        }

        try {
            min = (minDate != null && !minDate.isEmpty()) ? LocalDate.parse(minDate, formatter) : max.minusYears(1);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            min = max.minusYears(1); 
        }
        
		return repository.search1(min,max,name);
		
	}
	
	
}
