package com.example.small_project.ui.cheque;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;

import com.example.small_project.R;
import com.example.small_project.data.model.status_transaction.StatusTransactionEnum;
import com.example.small_project.databinding.FragmentTransactionChequeBinding;
import com.example.small_project.ui.utils.Utils;

import java.util.UUID;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class TransactionChequeFragment extends Fragment {

    private TransactionChequeViewModel viewModel;

    private FragmentTransactionChequeBinding binding;

    public TransactionChequeFragment() {
        super(R.layout.fragment_transaction_cheque);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                NavOptions navOptions = new NavOptions.Builder()
                        .setPopUpTo(R.id.inputTransactionFragment, true)
                        .build();

                NavHostFragment.findNavController(TransactionChequeFragment.this)
                        .navigate(R.id.inputTransactionFragment, null, navOptions);
            }
        };

        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentTransactionChequeBinding.inflate(inflater, container,false);

        viewModel = new ViewModelProvider(this).get(TransactionChequeViewModel.class);


        if (getArguments() != null) {
            TransactionChequeFragmentArgs args = TransactionChequeFragmentArgs.fromBundle(getArguments());
            String uuid = args.getUuid();
            UUID transactionId = UUID.fromString(uuid);

            StatusTransactionEnum status = args.getStatus();
            viewModel.setup(transactionId, status);
        }

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initObserve();
        binding.btnToolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavOptions navOptions = new NavOptions.Builder()
                        .setPopUpTo(R.id.inputTransactionFragment, true)
                        .build();

                NavHostFragment.findNavController(TransactionChequeFragment.this)
                        .navigate(R.id.inputTransactionFragment, null, navOptions);
            }
        });
    }

    private void initObserve() {
        viewModel.setTransaction.observe(getViewLifecycleOwner(), transaction -> {
            binding.chequeTransactionId.setText(transaction.getTransactionId().toString());
            binding.dateTime.setText(transaction.getDate());
            binding.amount.setText(Utils.formatAmountWithSpace(transaction.getAmount()));
        });

        viewModel.errorTransaction.observe(getViewLifecycleOwner(),str -> {
            binding.containerTransactionInfo.setVisibility(View.GONE);
            binding.tvError.setVisibility(View.VISIBLE);
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
