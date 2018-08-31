package com.lislon.sat.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.*;

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
}