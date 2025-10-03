package com.example.small_project.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.small_project.R;
import com.example.small_project.databinding.FragmentInputTransactionBinding;
import com.example.small_project.ui.utils.Utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import dagger.hilt.android.AndroidEntryPoint;
import jakarta.inject.Inject;

@AndroidEntryPoint
public class InputTransactionFragment extends Fragment{

    private FragmentInputTransactionBinding binding;

    private InputTransactionViewModel viewModel;

    public InputTransactionFragment() {
        super(R.layout.fragment_input_transaction);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentInputTransactionBinding.inflate(inflater,container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        viewModel = new ViewModelProvider(this).get(InputTransactionViewModel.class);

        initObserve(view);

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = binding.inputAmount.getText().toString().trim();
                long amount = Utils.parseSumString(text);
                viewModel.btnNext(amount);
            }
        });

        binding.inputAmount.addTextChangedListener(Utils.formatAsSum(binding.inputAmount, binding.btnNext));

        binding.btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections action = InputTransactionFragmentDirections.actionInputTransactionFragmentToHistoryFragment();
                Navigation.findNavController(view).navigate(action);
            }
        });

    }

    private void initObserve(View view) {
        viewModel.startConfirm.observe(getViewLifecycleOwner(),navigateEvent -> {
            if (navigateEvent != null){
                Long amount = navigateEvent.getAmount();
                startConfirmFragment(amount, view);
            }
        });
    }

    private void startConfirmFragment(Long amount,View view) {
        NavDirections action = InputTransactionFragmentDirections.actionInputTransactionFragmentToTransactionConfirmFragment(amount);
        Navigation.findNavController(view).navigate(action);
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
