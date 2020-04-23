package com.br.apiDivinaProvidencia.services;

import java.util.List;

import com.br.apiDivinaProvidencia.documents.ReportCashier;

public interface ReportCashierService {
	List<ReportCashier> findAll();
	ReportCashier insert(ReportCashier reportCashier);
	ReportCashier withdrawValue(ReportCashier reportCashier);
}
