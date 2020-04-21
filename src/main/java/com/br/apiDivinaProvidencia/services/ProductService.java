package com.br.apiDivinaProvidencia.services;

import java.util.List;

import com.br.apiDivinaProvidencia.documents.Product;


public interface ProductService {
	List<Product> findAll();
	Product findById(String id);
	Product insert (Product product);
	Product updateUser(Product product);
	void removeUser(String id);
}
