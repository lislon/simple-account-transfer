package com.lislon.sat.ws;

import com.lislon.sat.model.Account;
import com.lislon.sat.service.AccountsService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by ele on 30.08.2018.
 */
@Path("/accounts")
public class AccountsWs {
    @Inject
    private AccountsService accountsService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Account> getAccounts() {
        List<Account> accounts = accountsService.getAccounts();
        return accounts;
    }
}