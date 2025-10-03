package com.example.small_project.ui.confirm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.small_project.R;
import com.example.small_project.data.model.status_transaction.StatusTransactionEnum;
import com.example.small_project.databinding.FragmentTransactionConfirmBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class TransactionConfirmFragment extends Fragment {

    private FragmentTransactionConfirmBinding binding;

    private TransactionConfirmViewModel viewModel;

    public TransactionConfirmFragment() {
        super(R.layout.fragment_transaction_confirm);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentTransactionConfirmBinding.inflate(inflater, container, false);

        viewModel = new ViewModelProvider(this).get(TransactionConfirmViewModel.class);

        if (getArguments() != null) {
            TransactionConfirmFragmentArgs args = TransactionConfirmFragmentArgs.fromBundle(getArguments());
            Long amount = args.getAmount();
            viewModel.setup(amount);
        }

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initObserve(view);

        binding.btnToolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).popBackStack();
            }
        });

        binding.btnSuccess.setOnClickListener(v -> viewModel.onClickBtn(StatusTransactionEnum.SUCCESS));

        binding.btnError.setOnClickListener(v -> viewModel.onClickBtn(StatusTransactionEnum.ERROR));
    }

    private void initObserve(View view) {
        viewModel.startCheque.observe(getViewLifecycleOwner(), pair -> {
            NavDirections action = TransactionConfirmFragmentDirections.actionTransactionConfirmFragmentToTransactionChequeFragment(pair.first.toString(),pair.second);
            Navigation.findNavController(view).navigate(action);
        });

        viewModel.progress.observe(getViewLifecycleOwner(), show -> {
            if (show) {
                binding.progress.setVisibility(View.VISIBLE);
            }else {
                binding.progress.setVisibility(View.GONE);
            }
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
