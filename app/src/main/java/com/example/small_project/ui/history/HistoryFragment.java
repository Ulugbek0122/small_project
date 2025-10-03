package com.example.small_project.ui.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.small_project.R;
import com.example.small_project.databinding.FragmentHistoryBinding;
import com.example.small_project.ui.history.adapter.TransactionHistoryAdapter;
import com.example.small_project.ui.main.InputTransactionViewModel;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import dagger.hilt.android.lifecycle.HiltViewModel;

@AndroidEntryPoint
public class HistoryFragment extends Fragment {

    private FragmentHistoryBinding binding;

    private HistoryViewModel viewModel;

    private TransactionHistoryAdapter adapter;

    public HistoryFragment() {
        super(R.layout.fragment_history);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHistoryBinding.inflate(inflater, container,false);

        viewModel = new ViewModelProvider(this).get(HistoryViewModel.class);

        adapter = new TransactionHistoryAdapter();
        binding.rvAmount.setAdapter(adapter);

        return  binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initObserve();

        binding.btnToolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).popBackStack();
            }
        });

        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.deleteTransaction();
            }
        });
    }

    private void initObserve() {
        viewModel.progress.observe(getViewLifecycleOwner(),show -> {
            if (show) {
                binding.progress.setVisibility(View.VISIBLE);
            }else {
                binding.progress.setVisibility(View.GONE);
            }
        });

        viewModel.setTransactions.observe(getViewLifecycleOwner(), transactions -> {
            if (transactions.isEmpty()){
                binding.tvEmpty.setVisibility(View.VISIBLE);
            }
            adapter.submitItems(transactions);
        });
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        viewModel = null;
        super.onDestroy();
    }
}
