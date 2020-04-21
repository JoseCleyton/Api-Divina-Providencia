package com.br.apiDivinaProvidencia.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.apiDivinaProvidencia.documents.ReportCashier;
import com.br.apiDivinaProvidencia.repository.ReportCashierRepository;
import com.br.apiDivinaProvidencia.services.ReportCashierService;

@Service
public class ReportCashierServiceImpl implements ReportCashierService {
	@Autowired
	private ReportCashierRepository reportCashierRepository;

	@Override
	public List<ReportCashier> findAll() {
		return this.reportCashierRepository.findAll();
	}

	@Override
	public ReportCashier insert(ReportCashier reportCashier) {
		return this.reportCashierRepository.save(reportCashier);
	}

}
