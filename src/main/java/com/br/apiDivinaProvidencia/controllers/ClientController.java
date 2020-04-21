package com.br.apiDivinaProvidencia.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.apiDivinaProvidencia.services.ClientService;
import com.br.apiDivinaProvidencia.documents.Client;
import com.br.apiDivinaProvidencia.responses.Response;

@RestController
@RequestMapping(path = "/client")
public class ClientController {

	@Autowired
	private ClientService clientService;

	@GetMapping
	public ResponseEntity<List<Client>> findAll() {
		return ResponseEntity.ok(this.clientService.findAll());
	}

	@PostMapping
	public ResponseEntity<Client> insertClient(@RequestBody Client client) {
		return ResponseEntity.ok(this.clientService.insert(client));
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Response<Client>> findById(@PathVariable(name = "id") String id) {
		List<Client> clients = this.clientService.findAll();
		for (Client client : clients) {
			if (client.getId().equals(id)) {
				return ResponseEntity.ok(new Response<Client>(client));
			}
		}
		return ResponseEntity.badRequest().body(new Response<Client>("ID Inválido ou inexistente"));
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<Client> update(@PathVariable(name = "id") String id, @RequestBody Client client) {
		client.setId(id);
		return ResponseEntity.ok(this.clientService.updateUser(client));
	}

}
