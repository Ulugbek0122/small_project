package com.example.small_project.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.small_project.data.entity.Transaction;

import java.util.List;
import java.util.UUID;

import io.reactivex.rxjava3.core.Single;

@Dao
public interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Transaction transaction);

    @Query("SELECT * FROM `transaction` ORDER BY id DESC")
    Single<List<Transaction>> getAllTransactions();

    @Query("SELECT * FROM `transaction` WHERE transactionId = :transactionId LIMIT 1")
    Single<Transaction> getTransactionByTransactionId(UUID transactionId);

    @Query("DELETE FROM `transaction`")
    void delete();
}
