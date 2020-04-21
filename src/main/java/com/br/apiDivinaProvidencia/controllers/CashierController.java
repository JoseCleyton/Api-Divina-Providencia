package com.br.apiDivinaProvidencia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.ApplicationScope;

import com.br.apiDivinaProvidencia.documents.Cashier;
import com.br.apiDivinaProvidencia.repository.CashierRepository;
import com.br.apiDivinaProvidencia.services.CashierService;

@RestController
@RequestMapping (path="/cashier")
public class CashierController {
	@Autowired
	private CashierService cashierService;

	@GetMapping
	public ResponseEntity<Double> getValueCashier() {
		return ResponseEntity.ok(this.cashierService.getValueCashier());
	}
	@PostMapping
	public void insertValue(@RequestBody double value){		
		this.cashierService.updateCashier(value);
	}
	public void updateCashier(double valueOrder) {
		this.cashierService.updateCashier(valueOrder);
	}
}
