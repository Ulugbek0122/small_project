package com.example.small_project.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.small_project.data.converters.Converters;
import com.example.small_project.data.dao.TransactionDao;
import com.example.small_project.data.entity.Transaction;

@Database(entities = {Transaction.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract TransactionDao transactionDao();
}
