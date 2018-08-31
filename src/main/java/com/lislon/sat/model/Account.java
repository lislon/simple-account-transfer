package com.lislon.sat.model;

import javax.xml.bind.annotation.XmlRootElement;

public class Account {
    public Integer id;
    public Integer balance;

    public Account() {
        this.balance = 0;
    }

    public Account(Integer id, Integer balance) {
        this.id = id;
        this.balance = balance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public static Account createWithBalance(Integer balance) {
        Account account = new Account();
        account.setBalance(balance);
        return account;
    }
}
