package com.br.apiDivinaProvidencia.services;


import com.br.apiDivinaProvidencia.documents.Cashier;

public interface CashierService {
	double getValueCashier();
	Cashier insertValue(Cashier cashier);
	void updateCashier(double valueOrder);
}
