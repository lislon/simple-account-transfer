package com.lislon.sat.ws;

import com.lislon.sat.JerseyConfiguration;
import com.lislon.sat.model.Account;
import com.lislon.sat.model.Transaction;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;

@SuppressWarnings("unchecked")
public class TransactionsWsIT extends AbstractIT {

    @Override
    protected Application configure() {
        return new JerseyConfiguration();
    }

    @Test
    public void should_transfer_funds() {
        int accountId1 = createAccountWithBalance(100);
        int accountId2 = createAccountWithBalance(200);

        Transaction completedTransaction = sendMoney(accountId1, accountId2, 50);
        Assert.assertNotNull(completedTransaction.getId());
    }


    private Transaction sendMoney(int accountId1, int accountId2, int amount) {
        Entity<Transaction> request = Entity.json(
                Transaction.send(accountId1, accountId2, amount)
        );
        return target("/transactions").request().post(request, Transaction.class);
    }

    private int createAccountWithBalance(int balance) {
        Entity<Account> request = Entity.json(Account.createWithBalance(balance));
        Account account = target("accounts").request().post(request, Account.class);
        return account.getId();
    }
}
