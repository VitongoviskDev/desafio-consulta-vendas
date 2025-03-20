package com.devsuperior.dsmeta.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dsmeta.dto.ReportRequestDTO;
import com.devsuperior.dsmeta.dto.ReportResponseDTO;
import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SummaryRequestDTO;
import com.devsuperior.dsmeta.dto.SummaryResponseDTO;
import com.devsuperior.dsmeta.services.SaleService;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<Page<ReportResponseDTO>> getReport(
		@RequestParam(value = "minDate", defaultValue = "") String minDate,
		@RequestParam(value = "maxDate", defaultValue = "") String maxDate,
		@RequestParam(value = "name", defaultValue = "") String name,
		Pageable pageable
	) {

		ReportRequestDTO requestDTO = new ReportRequestDTO(minDate, maxDate, name);

		Page<ReportResponseDTO> response = service.getReport(requestDTO, pageable);

		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<?> getSummary(
		@RequestParam(value = "minDate", defaultValue = "") String minDate,
		@RequestParam(value = "maxDate", defaultValue = "") String maxDate,
		Pageable pageable
	) {
		SummaryRequestDTO requestDTO = new SummaryRequestDTO(minDate, maxDate);

		Page<SummaryResponseDTO> response = service.getSummary(requestDTO, pageable);

		return ResponseEntity.ok(response);
	}
}
