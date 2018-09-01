package com.lislon.sat.service;

import com.lislon.sat.model.Account;
import org.jvnet.hk2.annotations.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Service
public class AccountsService {

    public AccountsService() {
    }

    private HashMap<Integer, Account> accounts = new HashMap<>();

    public void populateAccounts(List<Account> accounts) {
        accounts.forEach(this::add);
    }

    public Collection<Account> getAccounts() {
        return accounts.values();
    }

    public void add(Account account) {
        account.setId(accounts.size() + 1);
        accounts.put(account.getId(), account);
    }

    public Account get(int accountId) {
        return accounts.get(accountId);
    }
}
