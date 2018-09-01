package com.lislon.sat.service;

import com.lislon.sat.model.Account;
import com.lislon.sat.model.TransferResult;

public class TransactionsService {

    private final AccountsService accountsService;

    public TransactionsService(AccountsService accountsService) {
        this.accountsService = accountsService;
    }

    public TransferResult transfer(Integer senderAccountId, Integer recipientAccountId, int amount) {
        Account from = accountsService.get(senderAccountId);
        Account to = accountsService.get(recipientAccountId);

        if (from == null) {
            return TransferResult.SENDER_ACCOUNT_NOT_EXISTS;
        }

        if (to == null) {
            return TransferResult.RECEIVER_ACCOUNT_NOT_EXISTS;
        }

        if (from.getBalance() < amount) {
            return TransferResult.INSUFFICIENT_FUNDS;
        }

        from.withdraw(amount);
        to.deposit(amount);
        return TransferResult.SUCCESS;
    }
}
