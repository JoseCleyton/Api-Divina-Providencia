package com.br.apiDivinaProvidencia.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.br.apiDivinaProvidencia.documents.Client;

public interface ClientRepository extends MongoRepository<Client, String>{

}
