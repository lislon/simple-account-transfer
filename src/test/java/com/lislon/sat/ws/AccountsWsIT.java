package com.lislon.sat.ws;

import com.lislon.sat.JerseyConfiguration;
import com.lislon.sat.model.Account;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import java.util.List;

@SuppressWarnings("unchecked")
public class AccountsWsIT extends AbstractIT {

    @Override
    protected Application configure() {
        return new JerseyConfiguration();
    }

    @Test
    public void should_return_some_predefined_accounts() {
        List<Account> accounts = (List<Account>) target("accounts").request().get(List.class);
        Assert.assertEquals(2, accounts.size());
    }

    @Test
    public void should_successfully_add_account() {
        Entity<Account> request = Entity.json(new Account());

        Account account = target("accounts").request().post(request, Account.class);
        Assert.assertNotNull(account.getId());
        Assert.assertEquals(Integer.valueOf(0),  account.getBalance());
    }
}
