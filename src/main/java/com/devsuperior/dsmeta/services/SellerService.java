package com.devsuperior.dsmeta.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.projections.SellerProjection;
import com.devsuperior.dsmeta.repositories.SellerRepository;

@Service
public class SellerService {
	
	@Autowired
	private SellerRepository repository;

	public List<SellerProjection> searchName(String minDate, String maxDate){
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

        return repository.search1(min, max);
    }	
	}

