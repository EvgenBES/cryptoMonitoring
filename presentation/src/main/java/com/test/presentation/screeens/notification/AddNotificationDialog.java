package com.test.presentation.screeens.notification;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.test.com.testproject.R;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.test.domain.entity.Coin;
import com.test.domain.usecases.AddNotifUseCase;
import com.test.presentation.screeens.main.StartViewActivity;

import javax.inject.Inject;

public class AddNotificationDialog extends DialogFragment {
    private static final String TAG = "AddNotificationDialog";

    private EditText priceEdit;
    private EditText searchCoin;
    private TextView actionAdd;
    private TextView actionBack;
    private RadioGroup radioGroup;
    private RadioButton radioButtonBelow;
    private boolean motion = true;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.item_notif_dialog, container, false);

        priceEdit = view.findViewById(R.id.notifPrice);
        actionAdd = view.findViewById(R.id.actionAdd);
        actionBack = view.findViewById(R.id.actionBack);
        radioGroup = view.findViewById(R.id.radioGroup);
        radioButtonBelow = view.findViewById(R.id.radioButtonBelow);


        actionAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int price = 0;
                if (!priceEdit.getText().toString().equals("")) {
                    price = Integer.valueOf(priceEdit.getText().toString());
                }


                if (radioGroup.getCheckedRadioButtonId() == radioButtonBelow.getId()) {
                    motion = false;
                } else {
                    motion = true;
                }

                ((NotifViewActivity) getActivity()).addNotificationBd(new Coin(0, "Name", "Symbol", price, motion));
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
