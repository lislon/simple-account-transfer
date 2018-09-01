package com.lislon.sat.service;

import com.lislon.sat.model.Account;
import com.lislon.sat.model.TransferResult;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TransactionsServiceTest {

    public static final int NON_EXISTING_ACCOUNT_ID = 123;

    private TransactionsService service;
    private AccountsService accountsService;

    @Before
    public void setUp() throws Exception {
        accountsService = new AccountsService();
        service = new TransactionsService(accountsService);
    }

    @Test
    public void testSuccessfulTransaction() {
        Account sender = givenAccount(100);
        Account recipient = givenAccount(100);

        TransferResult result = service.transfer(sender.getId(), recipient.getId(), 50);

        assertThat(sender.getBalance(), is(50));
        assertThat(recipient.getBalance(), is(150));
        assertThat(result, is(TransferResult.SUCCESS));
    }

    @Test
    public void testInsufficientFundsTransfer() {
        Account sender = givenAccount(10);
        Account recipient = givenAccount(50);

        TransferResult result = service.transfer(sender.getId(), recipient.getId(), 50);

        assertThat(result, is(TransferResult.INSUFFICIENT_FUNDS));
        assertThat(sender.getBalance(), is(10));
        assertThat(recipient.getBalance(), is(50));
    }

    @Test
    public void testTransferFromNonExistingAccount() {
        Account recipient = givenAccount(10);

        TransferResult result = service.transfer(NON_EXISTING_ACCOUNT_ID, recipient.getId(), 50);

        assertThat(result, is(TransferResult.SENDER_ACCOUNT_NOT_EXISTS));
    }

    @Test
    public void testTransferToNonExistingAccount() {
        Account sender = givenAccount(10);

        TransferResult result = service.transfer(sender.getId(), NON_EXISTING_ACCOUNT_ID, 50);

        assertThat(result, is(TransferResult.RECEIVER_ACCOUNT_NOT_EXISTS));
    }

    private Account givenAccount(int balance) {
        Account account = Account.createWithBalance(balance);
        accountsService.add(account);
        return account;
    }
}
