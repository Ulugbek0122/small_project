package com.example.small_project.ui.confirm;

import android.util.Pair;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.small_project.data.entity.Transaction;
import com.example.small_project.data.model.status_transaction.StatusTransactionEnum;
import com.example.small_project.data.repository.TransactionRepository;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.UUID;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class TransactionConfirmViewModel extends ViewModel {

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final TransactionRepository transactionRepository;
    private Long amount;

    @Inject
    public TransactionConfirmViewModel(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    private final MutableLiveData<Pair<UUID,StatusTransactionEnum>> _startCheque = new MutableLiveData<>();
    public LiveData<Pair<UUID,StatusTransactionEnum>> startCheque = _startCheque;


    private final MutableLiveData<Boolean> _progress = new MutableLiveData<>();
    public LiveData<Boolean> progress = _progress;
    public void setup(Long amount) {
        this.amount = amount;
    }

    public void onClickBtn(StatusTransactionEnum status) {
        Date now = new Date(System.currentTimeMillis());
        String date = formatDate(now);
        UUID transactionId = UUID.randomUUID();
        Transaction transaction = new Transaction(transactionId, date, amount, status);
        insertTransaction(transaction,transactionId);

    }



    public static String formatDate(Date date) {
        if (date == null) return null;
        SimpleDateFormat formatter = new SimpleDateFormat("MM.dd.yyyy HH:mm", Locale.getDefault());
        return formatter.format(date);
    }

    private void insertTransaction(Transaction transaction, UUID transactionId) {
        if (transaction.getStatusTransactionEnum().equals(StatusTransactionEnum.ERROR)){
            _startCheque.setValue(new Pair(transactionId, StatusTransactionEnum.ERROR));
        } else {
            _progress.setValue(true);
            compositeDisposable.add(transactionRepository.insert(transaction).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe(() -> {
                        _progress.setValue(false);
                        _startCheque.postValue(new Pair(transactionId, StatusTransactionEnum.SUCCESS));
                    },throwable -> {
                        _progress.setValue(false);
                    }));
        }

    }

    @Override
    protected void onCleared() {
        compositeDisposable = null;
        super.onCleared();
    }
}
