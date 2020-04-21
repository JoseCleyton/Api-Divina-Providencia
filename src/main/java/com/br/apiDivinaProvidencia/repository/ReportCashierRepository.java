package com.br.apiDivinaProvidencia.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.br.apiDivinaProvidencia.documents.ReportCashier;

public interface ReportCashierRepository extends MongoRepository<ReportCashier, String> {

}
