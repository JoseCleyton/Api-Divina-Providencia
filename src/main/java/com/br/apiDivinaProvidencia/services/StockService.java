package com.br.apiDivinaProvidencia.services;

import java.util.List;

import com.br.apiDivinaProvidencia.documents.Product;

public interface StockService {
	List<Product> findAll();
	Product findById(String id);
	Product insert (Product product);
	Product update(Product product);
	void remove(String id);
}
