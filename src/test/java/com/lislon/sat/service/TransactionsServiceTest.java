package com.lislon.sat.service;

import com.lislon.sat.model.Account;
import com.lislon.sat.model.TransferResult;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TransactionsServiceTest {
    TransactionsService service;

    private AccountsService accountsService;

    @Before
    public void setUp() throws Exception {
        accountsService = new AccountsService();
        service = new TransactionsService(accountsService);
    }

    @Test
    public void testSuccessfulTransaction() {
        Account account1 = givenAccount(100);
        Account account2 = givenAccount(100);

        TransferResult result = service.transfer(account1.getId(), account2.getId(), 50);

        assertThat(account1.getBalance(), is(50));
        assertThat(account2.getBalance(), is(150));
        assertThat(result, is(TransferResult.SUCCESS));
    }

    @Test
    public void testInsufficientFundsTransfer() {
        Account account1 = givenAccount(10);
        Account account2 = givenAccount(50);

        TransferResult result = service.transfer(account1.getId(), account2.getId(), 50);
        assertThat(result, is(TransferResult.INSUFFICIENT_FUNDS));
        assertThat(account1.getBalance(), is(10));
        assertThat(account2.getBalance(), is(50));
    }

    private Account givenAccount(int balance) {
        Account account = Account.createWithBalance(balance);
        accountsService.add(account);
        return account;
    }
}
