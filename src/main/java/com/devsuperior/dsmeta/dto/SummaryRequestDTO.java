package com.devsuperior.dsmeta.dto;

public class SummaryRequestDTO {
    private String minDate;
	private String maxDate;
	
	public SummaryRequestDTO(String minDate, String maxDate) {
		this.minDate = minDate;
		this.maxDate = maxDate;
	}

    public String getMinDate() {
        return minDate;
    }

    public String getMaxDate() {
        return maxDate;
    }
}
