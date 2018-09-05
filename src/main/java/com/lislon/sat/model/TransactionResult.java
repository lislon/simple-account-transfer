package com.lislon.sat.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description="Result of transaction")
public class TransactionResult {
    public TransactionStatus status;

    public TransactionResult() {
    }

    public TransactionResult(TransactionStatus status) {
        this.status = status;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public static TransactionResult create(TransactionStatus status) {
        return new TransactionResult(status);
    }
}
