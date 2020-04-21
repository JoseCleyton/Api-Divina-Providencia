package com.br.apiDivinaProvidencia.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.br.apiDivinaProvidencia.documents.User;

public interface UserRepository extends MongoRepository<User, String> {
	User findByLogin(String login);
}
