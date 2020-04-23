package com.br.apiDivinaProvidencia.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.br.apiDivinaProvidencia.documents.Cashier;
import com.br.apiDivinaProvidencia.repository.CashierRepository;
import com.br.apiDivinaProvidencia.services.CashierService;

@Service
public class CashierServiceImpl implements CashierService {
	private double value = 0;
	@Autowired
	private CashierRepository cashierRepository;

	@Override
	public double getValueCashier() {
		this.value = 0;
		this.cashierRepository.findAll().forEach(c -> {
			this.value += c.getValueCashier();
		});
		return value;
	}

	@Override
	public void updateCashier(double valueOrder, String operation) {
		double valueCashier = getValueCashier();
		if (operation.trim().equals("insert")) {
			valueCashier += valueOrder;
		} else if (operation.trim().equals("withdraw")) {
			valueCashier -= valueOrder;
		}
		Cashier cashier = new Cashier();
		List<Cashier> cashierList = this.cashierRepository.findAll();
		for (Cashier c : cashierList) {
			cashier = c;
		}
		cashier.setValueCashier(valueCashier);
		this.cashierRepository.save(cashier);
	}

}
