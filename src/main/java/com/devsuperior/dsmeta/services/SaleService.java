package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SaleSumaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
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

	public Page<SaleReportDTO> searchSalesRepost(String minDate, String maxDate, String name, Pageable pageable) {
		LocalDate min = initialDateEmpty(minDate);
		LocalDate max = finalDateEmpty(maxDate);

		return repository.searchSalesRepost(min, max, name, pageable);
	}

	public Page<SaleSumaryDTO> seatchSalesSumary(String minDate, String maxDate, Pageable pageable) {
		LocalDate min = initialDateEmpty(minDate);
		LocalDate max = finalDateEmpty(maxDate);

		return repository.searchSalesSumary(min, max, pageable);
	}

	private LocalDate initialDateEmpty(String initialDate) {
		if(initialDate.equals("")) {
			return LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault()).minusYears(1L);
		}
		return LocalDate.parse(initialDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}

	private LocalDate finalDateEmpty(String finalDate) {
		if(finalDate.equals("")) {
			return LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		}
		return LocalDate.parse(finalDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
}
