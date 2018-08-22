package com.test.presentation.screeens.notification;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.test.com.testproject.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.test.presentation.screeens.main.StartViewActivity;

public class EditNotificationDialog extends DialogFragment {
    private static final String TAG = "EditNotificationDialog";

    private EditText editQuantity;
    private TextView actionAdd;
    private TextView actionBack;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.item_notif_edit_dialog, container, false);

        editQuantity = view.findViewById(R.id.coinQuentity);
        actionAdd = view.findViewById(R.id.actionAdd);
        actionBack = view.findViewById(R.id.actionBack);

        actionAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String quantity = editQuantity.getText().toString();
                if (quantity.equals("")) {
                    quantity = "0";
                }
                ((StartViewActivity)getActivity()).loadQuantityCoin(quantity);
                getDialog().dismiss();
            }
        });

        actionBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              getDialog().dismiss();
            }
        });

        return view;
    }
}
