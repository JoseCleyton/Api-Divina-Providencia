package com.br.apiDivinaProvidencia.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.br.apiDivinaProvidencia.documents.User;

public interface UserRepository extends MongoRepository<User, String> {
	Optional<User> findByLogin(String login);
}
