package com.lislon.sat.model;


public class Transaction {
    public Integer id;

    public Integer from;
    public Integer to;
    public Integer amount;

    public static Transaction send(Integer from, Integer to, Integer amount) {
        return new Transaction(from, to, amount);
    }

    public Transaction() {
    }

    public Transaction(Integer from, Integer to, Integer amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
