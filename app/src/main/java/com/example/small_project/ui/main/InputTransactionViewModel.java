package com.example.small_project.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.small_project.data.models.NavigateEvent;
import com.example.small_project.data.models.SingleLiveEvent;
import com.example.small_project.data.repository.TransactionRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class InputTransactionViewModel extends ViewModel {

    private final TransactionRepository transactionRepository;

    private final SingleLiveEvent<NavigateEvent> _startConfirm = new SingleLiveEvent<>();
    public LiveData<NavigateEvent> startConfirm = _startConfirm;

    @Inject
    public InputTransactionViewModel(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void btnNext(Long amount) {
        _startConfirm.setValue(new NavigateEvent(amount));
    }
}
