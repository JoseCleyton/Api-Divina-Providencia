package com.br.apiDivinaProvidencia.documents;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Order {

	@Id
	private String id;
	private String orderDate;
	private String status;
	private String dateClose;
	private String client;
	private List<OrderIten> orderItens;
	private double orderValue;
	private String comments;
	private int orderMonth;
	
	public Order() {
		
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDateClose() {
		return dateClose;
	}
	public void setDateClose(String dateClose) {
		this.dateClose = dateClose;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public List<OrderIten> getOrderItens() {
		return orderItens;
	}
	public void setOrderItens(List<OrderIten> orderItens) {
		this.orderItens = orderItens;
	}
	public double getOrderValue() {
		return orderValue;
	}
	public void setOrderValue(double orderValue) {
		this.orderValue = orderValue;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public int getOrderMonth() {
		return orderMonth;
	}
	public void setOrderMonth(int orderMonth) {
		this.orderMonth = orderMonth;
	}
	
}
