package com.lislon.sat.model;


import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description="Used to specify details for transaction")
public class Transaction {

    @Schema(description="Senders account identifier")
    public Integer senderAccountId;

    @Schema(description="Receiver account identifier")
    public Integer receiverAccountId;

    @Schema(description="Amount of money to transfer")
    public Integer amount;

    public static Transaction send(Integer from, Integer to, Integer amount) {
        return new Transaction(from, to, amount);
    }

    public Transaction() {
    }

    public Transaction(Integer senderAccountId, Integer receiverAccountId, Integer amount) {
        this.senderAccountId = senderAccountId;
        this.receiverAccountId = receiverAccountId;
        this.amount = amount;
    }


    public Integer getSenderAccountId() {
        return senderAccountId;
    }

    public void setSenderAccountId(Integer senderAccountId) {
        this.senderAccountId = senderAccountId;
    }

    public Integer getReceiverAccountId() {
        return receiverAccountId;
    }

    public void setReceiverAccountId(Integer receiverAccountId) {
        this.receiverAccountId = receiverAccountId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
