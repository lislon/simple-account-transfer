package com.lislon.sat.service;

import com.lislon.sat.model.Account;
import org.jvnet.hk2.annotations.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountsService {

    private ArrayList<Account> accounts = new ArrayList<>();

    public void populateAccounts(List<Account> accounts) {
        this.accounts.addAll(accounts);
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void add(Account account) {
        account.id = accounts.size() + 1;
        accounts.add(account);
    }
}
