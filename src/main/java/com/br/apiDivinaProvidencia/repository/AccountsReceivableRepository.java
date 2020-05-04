package com.br.apiDivinaProvidencia.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.br.apiDivinaProvidencia.documents.AccountsReceivable;

public interface AccountsReceivableRepository extends MongoRepository<AccountsReceivable, String> {
}
