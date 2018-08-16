package com.test.presentation.screeens.list.fragment.left;

import android.test.com.testproject.databinding.ItemLeftFragmBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.test.domain.entity.Coin;
import com.test.presentation.base.recycler.BaseItemViewHolder;

public class LeftItemFragmentHolder extends BaseItemViewHolder
        <Coin, LeftItemFragmentModel, ItemLeftFragmBinding>{


    private LeftItemFragmentHolder(LeftItemFragmentModel viewModel, ItemLeftFragmBinding binding) {
        super(viewModel, binding);
    }

    public static LeftItemFragmentHolder create(ViewGroup parent, LeftItemFragmentModel viewModel) {
        ItemLeftFragmBinding binding = ItemLeftFragmBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return  new LeftItemFragmentHolder(viewModel, binding);
    }
}
