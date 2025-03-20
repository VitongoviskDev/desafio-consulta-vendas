package com.devsuperior.dsmeta.dto;

public class ReportRequestDTO {
    private String minDate;
	private String maxDate;
	private String name;
	
	public ReportRequestDTO(String minDate, String maxDate, String name) {
		this.minDate = minDate;
		this.maxDate = maxDate;
		this.name = name;
	}

    public String getMinDate() {
        return minDate;
    }

    public String getMaxDate() {
        return maxDate;
    }

    public String getName() {
        return name;
    }
}
