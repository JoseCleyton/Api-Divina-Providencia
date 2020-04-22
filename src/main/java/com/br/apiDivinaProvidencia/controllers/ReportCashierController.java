package com.br.apiDivinaProvidencia.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.apiDivinaProvidencia.documents.ReportCashier;
import com.br.apiDivinaProvidencia.services.ReportCashierService;

@RestController
@RequestMapping(path = "/reportCashier")
public class ReportCashierController {
	@Autowired
	private ReportCashierService reportCashierService;

	@GetMapping
	public ResponseEntity<List<ReportCashier>> findAll() {
		return ResponseEntity.ok(this.reportCashierService.findAll());
	}

	@PostMapping
	public ResponseEntity<ReportCashier> insert(@RequestBody ReportCashier reportCashier) {
		return ResponseEntity.ok(this.reportCashierService.insert(reportCashier));
	}

}
