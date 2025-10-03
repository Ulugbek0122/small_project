package com.example.small_project.ui.history;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.small_project.data.entity.Transaction;
import com.example.small_project.data.repository.TransactionRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class HistoryViewModel extends ViewModel {

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final TransactionRepository transactionRepository;

    private final MutableLiveData<List<Transaction>> _setTransactions = new MutableLiveData<>();
    public LiveData<List<Transaction>> setTransactions = _setTransactions;

    private final MutableLiveData<Boolean> _progress = new MutableLiveData<>();
    public LiveData<Boolean> progress = _progress;

    @Inject
    public HistoryViewModel(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
        getAllTransfer();
    }


    public void getAllTransfer() {
        _progress.setValue(true);
        compositeDisposable.add(transactionRepository.getAllTransaction().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(transactions -> {
                    _progress.setValue(false);
                    _setTransactions.setValue(transactions);
                }, throwable -> {
                    _progress.setValue(false);
                }));
    }


    public void deleteTransaction() {
        _progress.setValue(true);
        compositeDisposable.add(transactionRepository.delete().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(() -> {
                    _progress.setValue(false);
                    _setTransactions.setValue(new ArrayList<>());
                }, throwable -> {
                    _progress.setValue(false);
                }));
    }


    @Override
    protected void onCleared() {
        compositeDisposable = null;
        super.onCleared();
    }
}
