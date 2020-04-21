package com.br.apiDivinaProvidencia.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ReportCashier {
	@Id
	private String id;
	private String comments;
	private String date;
	private double value;
	private double oldValueCashier;
	private String reportType;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public double getOldValueCashier() {
		return oldValueCashier;
	}

	public void setOldValueCashier(double oldValueCashier) {
		this.oldValueCashier = oldValueCashier;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

}
