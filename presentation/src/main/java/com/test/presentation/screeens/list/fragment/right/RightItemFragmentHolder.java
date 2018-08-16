package com.test.presentation.screeens.list.fragment.right;

import android.test.com.testproject.databinding.ItemRightFragmBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.test.domain.entity.Coin;
import com.test.presentation.base.recycler.BaseItemViewHolder;

public class RightItemFragmentHolder extends BaseItemViewHolder
        <Coin, RightItemFragmentModel, ItemRightFragmBinding>{


    private RightItemFragmentHolder(RightItemFragmentModel viewModel, ItemRightFragmBinding binding) {
        super(viewModel, binding);
    }

    public static RightItemFragmentHolder create(ViewGroup parent, RightItemFragmentModel viewModel) {
        ItemRightFragmBinding binding = ItemRightFragmBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return  new RightItemFragmentHolder(viewModel, binding);
    }
}
