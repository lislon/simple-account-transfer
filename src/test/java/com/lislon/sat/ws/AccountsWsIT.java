package com.lislon.sat.ws;

import com.lislon.sat.JerseyConfiguration;
import com.lislon.sat.model.Account;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Application;
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
}
