package com.example.small_project.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.small_project.data.repository.TransactionRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class InputTransactionViewModel extends ViewModel {

    private final TransactionRepository transactionRepository;

    private final MutableLiveData<Long> _startConfirm = new MutableLiveData<>();
    public LiveData<Long> startConfirm = _startConfirm;

    @Inject
    public InputTransactionViewModel(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void btnNext(Long amount) {
        _startConfirm.setValue(amount);
    }
}
