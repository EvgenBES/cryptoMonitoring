package com.test.presentation.screeens.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.test.com.testproject.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class EditQuantityDialog extends DialogFragment {

    private EditText editQuantity;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.item_start_dialog, container);

        editQuantity = (EditText) view.findViewById(R.id.coinQuentity);

        return getView();
    }
}
