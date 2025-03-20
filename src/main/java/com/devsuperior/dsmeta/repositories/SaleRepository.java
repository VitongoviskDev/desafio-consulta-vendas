package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devsuperior.dsmeta.dto.ReportResponseDTO;
import com.devsuperior.dsmeta.dto.SummaryResponseDTO;
import com.devsuperior.dsmeta.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query("SELECT new com.devsuperior.dsmeta.dto.ReportResponseDTO(obj.id, obj.date, obj.amount, obj.seller.name) " +
            "FROM Sale obj " +
            "WHERE UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :name, '%')) " +
            "AND obj.date BETWEEN :minDate AND :maxDate")
    Page<ReportResponseDTO> getReport(
        String name,
        LocalDate minDate,
        LocalDate maxDate,
        Pageable pageable
    );

    @Query("SELECT new com.devsuperior.dsmeta.dto.SummaryResponseDTO(obj.seller.name, SUM(obj.amount)) " +
            "FROM Sale obj " +
            "WHERE obj.date BETWEEN :minDate AND :maxDate " +
            "GROUP BY obj.seller.name")
    Page<SummaryResponseDTO> getSummary(
        @Param("minDate") LocalDate minDate,
        @Param("maxDate") LocalDate maxDate,
        Pageable pageable
    );
}
