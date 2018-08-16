package com.test.presentation.screeens.notification;

import android.test.com.testproject.databinding.ItemNotifBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.test.domain.entity.Coin;
import com.test.presentation.base.recycler.BaseItemViewHolder;

public class NotifItemViewHolder extends BaseItemViewHolder
        <Coin, NotifItemViewModel, ItemNotifBinding>{


    private NotifItemViewHolder(NotifItemViewModel viewModel, ItemNotifBinding binding) {
        super(viewModel, binding);
    }

    public static NotifItemViewHolder create(ViewGroup parent, NotifItemViewModel viewModel) {
        ItemNotifBinding binding = ItemNotifBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return  new NotifItemViewHolder(viewModel, binding);
    }
}
