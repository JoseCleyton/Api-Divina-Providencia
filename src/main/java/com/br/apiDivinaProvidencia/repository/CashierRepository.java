package com.br.apiDivinaProvidencia.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.br.apiDivinaProvidencia.documents.Cashier;

public interface CashierRepository extends MongoRepository<Cashier, String> {

}
