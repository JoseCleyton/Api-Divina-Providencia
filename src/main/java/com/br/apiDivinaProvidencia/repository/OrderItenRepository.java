package com.br.apiDivinaProvidencia.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.br.apiDivinaProvidencia.documents.OrderIten;

public interface OrderItenRepository extends MongoRepository<OrderIten, String>{

}
