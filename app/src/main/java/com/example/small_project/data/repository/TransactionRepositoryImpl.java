package com.example.small_project.data.repository;


import com.example.small_project.data.dao.TransactionDao;
import com.example.small_project.data.entity.Transaction;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Singleton
public class TransactionRepositoryImpl implements TransactionRepository {

    private final TransactionDao transactionDao;

    @Inject
    public TransactionRepositoryImpl(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

    @Override
    public Completable insert(Transaction transaction) {
        return Completable.fromAction(() -> transactionDao.insert(transaction));
    }

    @Override
    public Single<List<Transaction>> getAllTransaction() {
        return transactionDao.getAllTransactions();
    }

    @Override
    public Completable delete() {
        return Completable.fromAction(transactionDao::delete);
    }

    @Override
    public Single<Transaction> getTransactionByTransactionId(UUID transactionId) {
        return transactionDao.getTransactionByTransactionId(transactionId);
    }
}
