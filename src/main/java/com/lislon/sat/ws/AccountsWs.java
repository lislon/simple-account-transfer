package com.lislon.sat.ws;

import com.lislon.sat.model.Account;
import com.lislon.sat.service.AccountsService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.List;

/**
 * Created by ele on 30.08.2018.
 */
@Path("/accounts")
@Produces(MediaType.APPLICATION_JSON)
public class AccountsWs {
    @Inject
    private AccountsService accountsService;

    @Inject
    private AccountsService accountsService2;

    @GET
    public Collection<Account> getAccounts() {
        return accountsService.getAccounts();
    }

    @GET
    @Path("{id}")
    public Account get(@PathParam("id") Integer id) {
        return accountsService.get(id);
    }

    @POST
    public Account addAccount(Account newAccount) {
        accountsService.add(newAccount);
        return newAccount;
    }
}