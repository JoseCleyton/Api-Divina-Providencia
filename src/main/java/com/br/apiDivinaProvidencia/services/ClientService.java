package com.br.apiDivinaProvidencia.services;

import java.util.List;

import com.br.apiDivinaProvidencia.documents.Client;



public interface ClientService {
	List<Client> findAll();
	Client findById(String id);
	Client insert (Client client);
	Client updateUser(Client client);
	void removeUser(String id);
}
