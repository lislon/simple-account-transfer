package com.lislon.sat.ws;

import com.lislon.sat.JerseyConfiguration;
import com.lislon.sat.model.Account;
import org.eclipse.jetty.http.HttpStatus;
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
        Account account = apiAddAccount(new Account());
        Assert.assertNotNull(account.getId());
        Assert.assertEquals(Integer.valueOf(0),  account.getBalance());
    }

    private Account apiAddAccount(Account account) {
        Entity<Account> request = Entity.json(account);

        return target("accounts").request().post(request, Account.class);
    }

    @Test
    public void should_get_account_info() {
        Account addedAccount = apiAddAccount(Account.createWithBalance(123));
        Account returnedAccount = apiGetAccount(addedAccount.getId());
        Assert.assertEquals(Integer.valueOf(123), returnedAccount.getBalance());
    }

    @Test
    public void should_get_not_existing_account_should_be_404() {
        Response response = target("accounts/123").request().get();
        Assert.assertEquals(HttpStatus.NOT_FOUND_404, response.getStatus());
    }

}
