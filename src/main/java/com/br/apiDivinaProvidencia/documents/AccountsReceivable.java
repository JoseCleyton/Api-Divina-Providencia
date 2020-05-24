package com.br.apiDivinaProvidencia.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class AccountsReceivable {

	@Id
	private String id;
	private Order order;
	private boolean checkout;
	private String dateCheckout;
	private int numberInstallments;
	private int paidInstallments;

	public String getId() {
		return id;
	}

	public boolean isCheckout() {
		return checkout;
	}

	public void setCheckout(boolean checkout) {
		this.checkout = checkout;
	}

	public String getDateCheckout() {
		return dateCheckout;
	}

	public void setDateCheckout(String dateCheckout) {
		this.dateCheckout = dateCheckout;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public int getNumberInstallments() {
		return numberInstallments;
	}

	public void setNumberInstallments(int numberInstallments) {
		this.numberInstallments = numberInstallments;
	}

	public int getPaidInstallments() {
		return paidInstallments;
	}

	public void setPaidInstallments(int paidInstallments) {
		this.paidInstallments = paidInstallments;
	}

}
