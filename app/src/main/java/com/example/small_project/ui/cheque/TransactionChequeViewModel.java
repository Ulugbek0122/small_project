package com.example.small_project.ui.cheque;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.small_project.data.entity.Transaction;
import com.example.small_project.data.model.status_transaction.StatusTransactionEnum;
import com.example.small_project.data.repository.TransactionRepository;

import java.util.UUID;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class TransactionChequeViewModel extends ViewModel {

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final TransactionRepository transactionRepository;

    @Inject
    public TransactionChequeViewModel(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    private final MutableLiveData<Transaction> _setTransaction= new MutableLiveData<>();
    public LiveData<Transaction> setTransaction = _setTransaction;

    private final MutableLiveData<String> _errorTransaction= new MutableLiveData<>();
    public LiveData<String> errorTransaction = _errorTransaction;

    private final MutableLiveData<Boolean> _progress = new MutableLiveData<>();
    public LiveData<Boolean> progress = _progress;

    private UUID transactionId;
    private StatusTransactionEnum status;
    public void setup(UUID transactionId, StatusTransactionEnum status) {
        this.transactionId = transactionId;
        this.status = status;
        if (status.equals(StatusTransactionEnum.ERROR)){
            _errorTransaction.setValue("Ошибка");
        }else {
            getTransaction();
        }
    }

    public void getTransaction() {
        _progress.setValue(true);
        compositeDisposable.add(transactionRepository.getTransactionByTransactionId(transactionId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(transaction -> {
                            _progress.setValue(false);
                            _setTransaction.setValue(transaction);
                },
                        throwable -> {
                            _progress.setValue(false);
                        }));
    }


    @Override
    protected void onCleared() {
        compositeDisposable = null;
        super.onCleared();
    }
}
