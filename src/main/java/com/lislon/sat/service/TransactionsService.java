package com.lislon.sat.service;

import com.lislon.sat.model.Account;
import com.lislon.sat.model.TransferResult;

public class TransactionsService {

    private final AccountsService accountsService;

    public TransactionsService(AccountsService accountsService) {
        this.accountsService = accountsService;
    }

    public TransferResult transfer(Integer fromId, Integer toId, int amount) {
        Account from = accountsService.get(fromId);
        Account to = accountsService.get(toId);

        if (from.getBalance() < amount) {
            return TransferResult.INSUFFICIENT_FUNDS;
        }

        from.withdraw(amount);
        to.deposit(amount);
        return TransferResult.SUCCESS;
    }
}
