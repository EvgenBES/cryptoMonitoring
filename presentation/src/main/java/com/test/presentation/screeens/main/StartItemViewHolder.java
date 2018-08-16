package com.test.presentation.screeens.main;

import android.test.com.testproject.databinding.ItemStartActivityElementBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.test.domain.entity.Coin;
import com.test.presentation.base.recycler.BaseItemViewHolder;

public class StartItemViewHolder extends BaseItemViewHolder
        <Coin, StartItemViewModel, ItemStartActivityElementBinding>{


    private StartItemViewHolder(StartItemViewModel viewModel, ItemStartActivityElementBinding binding) {
        super(viewModel, binding);
    }

    public static StartItemViewHolder create(ViewGroup parent, StartItemViewModel viewModel) {
        ItemStartActivityElementBinding binding = ItemStartActivityElementBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return  new StartItemViewHolder(viewModel, binding);
    }
}
