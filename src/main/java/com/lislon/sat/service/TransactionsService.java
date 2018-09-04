package com.lislon.sat.service;

import com.lislon.sat.model.Account;
import com.lislon.sat.model.TransferResult;
import org.jvnet.hk2.annotations.Service;

import javax.inject.Inject;

@Service
public class TransactionsService {

    private final AccountsService accountsService;

    @Inject
    public TransactionsService(AccountsService accountsService) {
        this.accountsService = accountsService;
    }

    /**
     * Transfer funds from between accounts.
     *
     * @param senderAccountId sender's account id.
     * @param recipientAccountId recipients's account id.
     * @param amount amount of money to transfer from account.
     * @return Result of transfer operation.
     */
    public TransferResult transfer(Integer senderAccountId, Integer recipientAccountId, int amount) {
        Account from = accountsService.get(senderAccountId);
        Account to = accountsService.get(recipientAccountId);

        if (from == null) {
            return TransferResult.SENDER_ACCOUNT_NOT_EXISTS;
        }

        if (to == null) {
            return TransferResult.RECEIVER_ACCOUNT_NOT_EXISTS;
        }

        // TODO: Unit test deadlock
        Object lock1 = from.getId() < to.getId() ? from : to;
        Object lock2 = from.getId() > to.getId() ? from : to;

        synchronized (lock1) {
            synchronized (lock2) {

                if (from.getBalance() < amount) {
                    return TransferResult.INSUFFICIENT_FUNDS;
                }

                from.withdraw(amount);
                to.deposit(amount);
            }
        }
        return TransferResult.SUCCESS;
    }
}
