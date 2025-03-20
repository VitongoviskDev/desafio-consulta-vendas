package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.ReportRequestDTO;
import com.devsuperior.dsmeta.dto.ReportResponseDTO;
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

	public Page<ReportResponseDTO> getReport(ReportRequestDTO dto, Pageable pageable) {
		LocalDate minDate = dto.getMinDate().isEmpty()
			? LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault()).minusYears(1L)
			: LocalDate.parse(dto.getMinDate());
		LocalDate maxDate = dto.getMaxDate().isEmpty() 
			? LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault())
			: LocalDate.parse(dto.getMaxDate());

		return repository.getReport(dto.getName(), minDate, maxDate, pageable);
	}
}
