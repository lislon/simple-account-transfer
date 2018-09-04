package com.lislon.sat.ws;

import com.lislon.sat.JerseyConfiguration;
import com.lislon.sat.model.Account;
import com.lislon.sat.model.Transaction;
import com.lislon.sat.model.TransactionDetails;
import com.lislon.sat.model.TransactionStatus;
import org.eclipse.jetty.http.HttpStatus;
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
    public void should_transfer_funds() throws Exception {
        int accountId1 = createAccountWithBalance(100);
        int accountId2 = createAccountWithBalance(200);

        TransactionDetails transferResult = sendMoney(accountId1, accountId2, 50).readEntity(TransactionDetails.class);
        Assert.assertEquals(TransactionStatus.SUCCESS, transferResult.getStatus());

        Account account1 = apiGetAccount(accountId1);
        Account account2 = apiGetAccount(accountId2);

        Assert.assertEquals(Integer.valueOf(50), account1.getBalance());
        Assert.assertEquals(Integer.valueOf(250), account2.getBalance());
    }

    @Test
    public void should_catch_insufficient_funds() throws Exception {
        int accountId1 = createAccountWithBalance(100);
        int accountId2 = createAccountWithBalance(200);

        javax.ws.rs.core.Response transferResult = sendMoney(accountId1, accountId2, 101);
        Assert.assertEquals(HttpStatus.CONFLICT_409, transferResult.getStatus());
    }

    private javax.ws.rs.core.Response sendMoney(int accountId1, int accountId2, int amount) {
        Entity<Transaction> request = Entity.json(
                Transaction.send(accountId1, accountId2, amount)
        );
        return target("/transactions").request().post(request);
    }

    private int createAccountWithBalance(int balance) {
        Entity<Account> request = Entity.json(Account.createWithBalance(balance));
        Account account = target("accounts").request().post(request, Account.class);
        return account.getId();
    }
}
