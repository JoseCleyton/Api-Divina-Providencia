package com.br.apiDivinaProvidencia.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.apiDivinaProvidencia.documents.AccountsReceivable;
import com.br.apiDivinaProvidencia.repository.AccountsReceivableRepository;
import com.br.apiDivinaProvidencia.services.AccountsReceivableService;

@Service
public class AccountsReceivableServiceImpl implements AccountsReceivableService {
	@Autowired
	private AccountsReceivableRepository accountsReceivableRepository;

	@Override
	public List<AccountsReceivable> findAll() {
		return this.accountsReceivableRepository.findAll();
	}

	@Override
	public AccountsReceivable checkout(AccountsReceivable accountsReceivable) {
		return this.accountsReceivableRepository.save(accountsReceivable);
	}

	@Override
	public AccountsReceivable checkin(AccountsReceivable accountsReceivable) {
		return this.accountsReceivableRepository.save(accountsReceivable);
	}

	@Override
	public AccountsReceivable findById(String id) {
		List<AccountsReceivable> accountsReceivables = this.accountsReceivableRepository.findAll();
		for (AccountsReceivable accountsReceivable : accountsReceivables) {
			if (accountsReceivable.getId() == id) {
				return accountsReceivable;
			}
		}
		return null;
	}

	@Override
	public AccountsReceivable payInstallments(AccountsReceivable accountsReceivable) {
		return this.accountsReceivableRepository.save(accountsReceivable);
	}

	@Override
	public List<AccountsReceivable> findOpens() {
		return this.accountsReceivableRepository.findAll().stream().filter(a -> !a.isCheckout())
				.collect(Collectors.toList());
	}

}
