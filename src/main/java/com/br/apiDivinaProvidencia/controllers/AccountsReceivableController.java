package com.br.apiDivinaProvidencia.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.apiDivinaProvidencia.documents.AccountsReceivable;
import com.br.apiDivinaProvidencia.documents.Order;
import com.br.apiDivinaProvidencia.services.AccountsReceivableService;

@RestController
@RequestMapping(path="accountsReceivable")
public class AccountsReceivableController {

	@Autowired
	private AccountsReceivableService accountsReceivableService;

	@GetMapping
	public ResponseEntity<List<AccountsReceivable>> findAll(){
		return ResponseEntity.ok(this.accountsReceivableService.findAll());
	}
	@PostMapping
	public ResponseEntity<AccountsReceivable> checkin(@RequestBody AccountsReceivable accountsReceivable){
		return ResponseEntity.ok(this.accountsReceivableService.checkin(accountsReceivable));
	}
	@PutMapping
	public ResponseEntity<AccountsReceivable> checkout(AccountsReceivable accountsReceivable){
		return ResponseEntity.ok(this.accountsReceivableService.checkout(accountsReceivable));
	}
}
