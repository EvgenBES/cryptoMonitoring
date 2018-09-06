package com.test.presentation.screeens.notification;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.test.com.testproject.R;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.test.presentation.screeens.main.StartViewActivity;

public class EditNotificationDialog extends DialogFragment {
    private static final String TAG = "EditNotificationDialog";

    private EditText notifPriceEdit;
    private TextView actionAdd;
    private TextView actionBack;
    private TextView actionDel;
    private TextView textName;
    private RadioButton radioButtonRedNotif;
    private RadioButton radioButtonGreenNotif;
    private boolean positionMotion;
    private int idCoin;
    private double editPrice;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_notif_edit_dialog, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        notifPriceEdit = view.findViewById(R.id.notifPriceEdit);
        actionAdd = view.findViewById(R.id.actionAdd);
        actionDel = view.findViewById(R.id.actionDelete);
        actionBack = view.findViewById(R.id.actionBack);
        textName = view.findViewById(R.id.coinNameNotifEditDialog);
        radioButtonRedNotif = view.findViewById(R.id.radioButtonRedNotif);
        radioButtonGreenNotif = view.findViewById(R.id.radioButtonGreenNotif);

        try {
            notifPriceEdit.setText(String.valueOf(getArguments().getDouble("massagePrice")));
            textName.setText("Coin: " + getArguments().getString("massageName"));
            positionMotion = getArguments().getBoolean("massageMotionPrice");
            idCoin = getArguments().getInt("massageIdNotif");
        } catch (NullPointerException npe) {
            ((NotifViewActivity) getActivity()).editNotificationBd(0, 0, false, false);
            getDialog().dismiss();
        }


        if (positionMotion) {
            radioButtonGreenNotif.setChecked(true);
            radioButtonRedNotif.setChecked(false);
        } else {
            radioButtonGreenNotif.setChecked(false);
            radioButtonRedNotif.setChecked(true);
        }


        actionAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    editPrice = Double.valueOf(notifPriceEdit.getText().toString());
                    ((NotifViewActivity) getActivity()).editNotificationBd(idCoin, editPrice, radioButtonGreenNotif.isChecked(), true);
                } catch (NumberFormatException nfx) {
                    ((NotifViewActivity) getActivity()).editNotificationBd(0, 0, false, false);
                }
                getDialog().dismiss();
            }
        });

        actionDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NotifViewActivity) getActivity()).deleteNotificationBd(idCoin);
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
