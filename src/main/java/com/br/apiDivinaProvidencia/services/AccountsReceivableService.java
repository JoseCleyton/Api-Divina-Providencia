package com.br.apiDivinaProvidencia.services;

import java.util.List;

import com.br.apiDivinaProvidencia.documents.AccountsReceivable;

public interface AccountsReceivableService {
	List<AccountsReceivable> findAll();
	AccountsReceivable checkout(AccountsReceivable accountsReceivable);
	AccountsReceivable checkin(AccountsReceivable accountsReceivable);
	
}
