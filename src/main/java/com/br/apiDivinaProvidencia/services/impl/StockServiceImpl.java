package com.br.apiDivinaProvidencia.services.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.apiDivinaProvidencia.documents.Product;
import com.br.apiDivinaProvidencia.repository.StockRepository;
import com.br.apiDivinaProvidencia.services.StockService;

@Service
public class StockServiceImpl implements StockService {

	@Autowired
	private StockRepository stockRepositoty;

	@Override
	public List<Product> findAll() {
		return this.stockRepositoty.findAll();
	}

	@Override
	public Product findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product insert(Product product) {
		return this.stockRepositoty.save(product);
	}

	@Override
	public Product update(Product product) {
		return this.stockRepositoty.save(product);
	}

	@Override
	public void remove(String id) {
		this.stockRepositoty.deleteById(id);
	}

}
