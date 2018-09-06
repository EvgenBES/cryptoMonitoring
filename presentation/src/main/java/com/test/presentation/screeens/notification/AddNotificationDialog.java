package com.test.presentation.screeens.notification;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.test.com.testproject.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

import io.reactivex.exceptions.OnErrorNotImplementedException;

public class AddNotificationDialog extends DialogFragment {
    private static final String TAG = "AddNotificationDialog";

    private EditText priceEdit;
    private AppCompatAutoCompleteTextView searchCoin;
    private TextView actionAdd;
    private TextView actionBack;
    private RadioGroup radioGroup;
    private RadioButton radioButtonBelow;
    private ArrayList<String> listCoins = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_notif_add_dialog, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        listCoins = getArguments().getStringArrayList("listSearchCoin");

        searchCoin = view.findViewById(R.id.searchCoin);
        priceEdit = view.findViewById(R.id.notifPrice);
        actionAdd = view.findViewById(R.id.actionAdd);
        actionBack = view.findViewById(R.id.actionBack);
        radioGroup = view.findViewById(R.id.radioGroup);
        radioButtonBelow = view.findViewById(R.id.radioButtonBelow);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                ((NotifViewActivity) getActivity(), R.layout.search_dialog_item, listCoins);
        searchCoin.setThreshold(1); //will start working from first character
        searchCoin.setAdapter(adapter);


        actionAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double price = 0;
                boolean motion;
                boolean result;
                String coinName = " ";


                try {
                    price = Double.valueOf(priceEdit.getText().toString());
                    result = true;
                } catch (NumberFormatException nfx) {
                    result = false;
                }

                if (radioGroup.getCheckedRadioButtonId() == radioButtonBelow.getId()) {
                    motion = false;
                } else {
                    motion = true;
                }

                if (result) {
                    for (String list : listCoins) {
                        if (list.equals(searchCoin.getText().toString())) {
                            coinName = searchCoin.getText().toString();
                            result = true;
                            break;
                        } else {
                            result = false;
                        }
                    }
                }

                ((NotifViewActivity) getActivity()).addNotificationBd(coinName, price, motion, result);
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
