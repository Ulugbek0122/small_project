package com.example.small_project.di;

import com.example.small_project.data.repository.TransactionRepository;
import com.example.small_project.data.repository.TransactionRepositoryImpl;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public abstract class TransactionRepositoryModule {


    @Binds
    @Singleton
    public abstract TransactionRepository provideTransactionRepository(TransactionRepositoryImpl impl);

}
