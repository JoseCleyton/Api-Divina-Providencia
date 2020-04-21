package com.br.apiDivinaProvidencia.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Product {

	@Id
	private String id;
	private String name;
	private double unitaryValue;
	private int quantityInStock;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getUnitaryValue() {
		return unitaryValue;
	}
	public void setUnitaryValue(double unitaryValue) {
		this.unitaryValue = unitaryValue;
	}
	public int getQuantityInStock() {
		return quantityInStock;
	}
	public void setQuantityInStock(int quantityInStock) {
		this.quantityInStock = quantityInStock;
	}
}
