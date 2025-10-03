package com.example.small_project.data.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.small_project.data.model.status_transaction.StatusTransactionEnum;

import java.sql.Date;
import java.util.UUID;

@Entity(tableName = "transaction")
public class Transaction {

    @PrimaryKey(autoGenerate = true)
    private Long id;
    private UUID transactionId;
    private String date;

    private Long amount;
    private StatusTransactionEnum statusTransactionEnum;


    public Transaction(UUID transactionId, String date, Long amount, StatusTransactionEnum statusTransactionEnum) {
        this.transactionId = transactionId;
        this.date = date;
        this.amount = amount;
        this.statusTransactionEnum = statusTransactionEnum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(UUID transactionId) {
        this.transactionId = transactionId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public StatusTransactionEnum getStatusTransactionEnum() {
        return statusTransactionEnum;
    }

    public void setStatusTransactionEnum(StatusTransactionEnum statusTransactionEnum) {
        this.statusTransactionEnum = statusTransactionEnum;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", transactionId=" + transactionId +
                ", date='" + date + '\'' +
                ", amount=" + amount +
                ", statusTransactionEnum=" + statusTransactionEnum +
                '}';
    }
}
