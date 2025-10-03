package com.example.small_project.di;

import android.content.Context;

import androidx.room.Room;

import com.example.small_project.data.AppDatabase;
import com.example.small_project.data.dao.TransactionDao;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import jakarta.inject.Singleton;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    @Provides
    @Singleton
    public AppDatabase provideDatabase(@ApplicationContext Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "local.db")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    public TransactionDao providetransactionDao(AppDatabase db) {
        return db.transactionDao();
    }

}
