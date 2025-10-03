package com.example.small_project.data.repository;

import androidx.lifecycle.LiveData;

import com.example.small_project.data.entity.Transaction;

import java.util.List;
import java.util.UUID;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public interface TransactionRepository {

    Completable insert(Transaction transaction);

    Single<Transaction> getTransactionByTransactionId(UUID transactionId);

    Single<List<Transaction>> getAllTransaction();
}
