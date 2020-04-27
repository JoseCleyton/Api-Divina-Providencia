package com.br.apiDivinaProvidencia.services.impl;

import java.util.List;

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

}
