package com.lislon.sat.service;

import com.lislon.sat.model.Account;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.*;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

public class AccountsServiceTest {

    private AccountsService service;

    @Before
    public void setUp() throws Exception {
        service = new AccountsService();
    }

    @Test
    public void getAccounts() {
        Assert.assertEquals(Collections.emptyList(), service.getAccounts());
    }

    @Test
    public void addAccount() {
        Account account = new Account();
        account.setBalance(100);

        service.add(account);

        assertNotNull(account.getId());
        assertThat(service.getAccounts(), contains(
                hasProperty("id", equalTo(1))
        ));
    }

    @Test
    public void getSingleAccount() {
        Account expected = Account.createWithBalance(10);
        service.add(expected);

        Account returnedAccount = service.get(expected.getId());
        assertReflectionEquals(expected, returnedAccount);
    }
}