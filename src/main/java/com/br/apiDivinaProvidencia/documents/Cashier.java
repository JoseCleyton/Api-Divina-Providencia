package com.br.apiDivinaProvidencia.documents;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Cashier {
	@Id
	private String id;
	private double valueCashier;

	public double getValueCashier() {
		return valueCashier;
	}

	public void setValueCashier(double valueCashier) {
		this.valueCashier = valueCashier;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
