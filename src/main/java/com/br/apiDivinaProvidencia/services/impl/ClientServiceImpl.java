package com.br.apiDivinaProvidencia.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.apiDivinaProvidencia.documents.Client;
import com.br.apiDivinaProvidencia.repository.ClientRepository;
import com.br.apiDivinaProvidencia.services.ClientService;

@Service
public class ClientServiceImpl implements ClientService{

	@Autowired
	private ClientRepository clientRepository;
	@Override
	public List<Client> findAll() {
		return this.clientRepository.findAll();
	}

	@Override
	public Client findById(String id) {
		return null;
	}

	@Override
	public Client insert(Client client) {
		return this.clientRepository.save(client);
	}

	@Override
	public Client updateUser(Client client) {
		
		return this.clientRepository.save(client);
	}

	@Override
	public void removeUser(String id) {
		this.clientRepository.deleteById(id);
		
	}

}
