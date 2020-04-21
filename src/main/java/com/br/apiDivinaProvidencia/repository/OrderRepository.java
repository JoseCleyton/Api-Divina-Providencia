package com.br.apiDivinaProvidencia.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.br.apiDivinaProvidencia.documents.Order;

public interface OrderRepository extends MongoRepository<Order, String>{

}
