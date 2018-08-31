package com.lislon.sat.model;

import javax.xml.bind.annotation.XmlRootElement;

public class Account {
    public int id;
    public int balance;

    public Account() {
    }

    public Account(int id, int balance) {
        this.id = id;
        this.balance = balance;
    }
}
