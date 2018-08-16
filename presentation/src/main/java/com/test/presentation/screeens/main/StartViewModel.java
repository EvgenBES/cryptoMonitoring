package com.test.presentation.screeens.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.databinding.ObservableField;
import android.test.com.testproject.R;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.EditText;
import android.widget.Toast;

import com.test.app.App;
import com.test.domain.entity.Coin;
import com.test.domain.entity.ItemTopStart;
import com.test.domain.usecases.EditQuentityUseCase;
import com.test.domain.usecases.GetBDCoinUseCase;
import com.test.presentation.base.BaseViewModel;
import com.test.presentation.base.recycler.ClickedItemModel;
import com.test.presentation.base.recycler.StartItemAdapter;
import com.test.presentation.utils.PieView;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class StartViewModel extends BaseViewModel<StartRouter, Coin> {

    public ObservableField<String> ballanceMoney = new ObservableField<>();

    public ObservableField<String> elemFirs = new ObservableField<>(" ");
    public ObservableField<String> elemSecond = new ObservableField<>(" ");
    public ObservableField<String> elemThird = new ObservableField<>(" ");

    public ObservableField<String> percentFirst = new ObservableField<>("0%");
    public ObservableField<String> percentSecond = new ObservableField<>("0%");
    public ObservableField<String> percentThird = new ObservableField<>("0%");
    public ObservableField<String> percentFourth = new ObservableField<>("0%");

    public ObservableField<Boolean> viewStib = new ObservableField<>(true);



    private Coin addQuentityCoin;

    @Inject
    public GetBDCoinUseCase getBDCoinUseCase;

    @Inject
    public EditQuentityUseCase editQuentityUseCase;

    public StartItemAdapter adapter = new StartItemAdapter();

    //формата для отображения цены
    private DecimalFormat formatDoubleZero = new DecimalFormat("#,##0.00");
    private DecimalFormat formatQuadrupleZero = new DecimalFormat("#0.00");

    @Override
    protected void runInject() {
        App.getAppComponent().runInject(this);
    }

    @Override
    public void setItem(Coin coin) {

    }

    public StartViewModel() {
        //Load user list coin local bd
        getBDCoinUseCase
                .getBdCoin()
                .subscribe(new Consumer<List<Coin>>() {
                    @Override
                    public void accept(List<Coin> coins) throws Exception {
                        adapter.setItems(coins);

                        loadTopElement(coins);
                    }

                })
                .isDisposed();



        //Edit Quantity coin
        adapter
                .observeItemClick()
                .subscribe(new Observer<ClickedItemModel<Coin>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ClickedItemModel<Coin> coinClickedItemModel) {
                        addQuentityCoin = coinClickedItemModel.getEntity();
                        onClickAddQuantityCoin();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        adapter
                .observeButtonOneClick()
                .subscribe(new Observer<ClickedItemModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ClickedItemModel clickedItemModel) {
                        router.goToListView();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    //TODO доделать реализацию диалогового окна (принятие данных и сохранение их в локальной бд)
    //TODO доделать динамичную отрисовку пончика!


    private void onClickAddQuantityCoin() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(router.getActivity(), AlertDialog.THEME_HOLO_DARK);
        LayoutInflater inflater = router.getActivity().getLayoutInflater();


        builder
                .setView(inflater.inflate(R.layout.item_start_dialog, null))
                // Add action buttons
                .setPositiveButton(R.string.addQuantity, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int id) {


                        addQuentityCoin.setQuantity(23.173);

                        editQuentityUseCase
                                .editCoin(addQuentityCoin);


                        //Show Toast
                        LayoutInflater inflater = router.getActivity().getLayoutInflater();
                        View toastLayout = inflater.inflate(R.layout.custom_edit_quantity_toast, (ViewGroup) router.getActivity().findViewById(R.id.custom_add_toast_image));

                        Toast toast = new Toast(App.getContext());
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 275);
                        toast.setView(toastLayout);
                        toast.show();


                    }
                })
                .setNegativeButton(R.string.back, null)
                .show();

    }


    private void loadTopElement(List<Coin> Coin) {

        //Creates new list item where have symbol coin and sumPrice (price * quantity)
        List<ItemTopStart> listItem = new ArrayList<>();
        for (Coin coins : Coin) {
            listItem.add(new ItemTopStart(coins.getSymbol(), coins.getPrice() * coins.getQuantity()));
        }


        //Where max sumPrice element
        Double maxElemFirs = 0.0;
        Double maxElemSecond = 0.0;
        Double maxElemThird = 0.0;

        for (int i = 0; i <= listItem.size() - 1; i++) {
            if (listItem.get(i).getSumPrice() > maxElemFirs) {
                maxElemThird = maxElemSecond;
                maxElemSecond = maxElemFirs;
                maxElemFirs = listItem.get(i).getSumPrice();
            } else if (listItem.get(i).getSumPrice() > maxElemSecond) {
                maxElemThird = maxElemSecond;
                maxElemSecond = listItem.get(i).getSumPrice();
            } else if (listItem.get(i).getSumPrice() > maxElemThird) {
                maxElemThird = listItem.get(i).getSumPrice();
            }
        }


        //Draw a head elemFirs
        if (listItem.size() != 0 && maxElemFirs != 0.0) {
            for (int i = 0; i <= listItem.size() - 1; i++) {
                if (listItem.get(i).getSumPrice().equals(maxElemFirs)) {
                    elemFirs.set(" " + listItem.get(i).getSymbol());
                }
            }
        }

        //Draw a head elemSecond
        if (listItem.size() != 0 && maxElemSecond != 0.0) {
            for (int i = 0; i <= listItem.size() - 1; i++) {
                if (listItem.get(i).getSumPrice().equals(maxElemSecond)) {
                    elemSecond.set(" " + listItem.get(i).getSymbol());
                }
            }
        }

        //Draw a head elemThird
        if (listItem.size() != 0 && maxElemThird != 0.0) {
            for (int i = 0; i <= listItem.size() - 1; i++) {
                if (listItem.get(i).getSumPrice().equals(maxElemThird)) {
                    elemThird.set(" " + listItem.get(i).getSymbol());
                }
            }
        }


        //Total money
        Double sumAllListCoin = 0.0;
        for (ItemTopStart itemTopStart : listItem) {
            sumAllListCoin += itemTopStart.getSumPrice();
        }

        //Save 1% and 4 position PieView
        Double onePercent = (sumAllListCoin / 100);
        float piePositionFirst = 0.0f;
        float piePositionSecond = 0.0f;
        float piePositionThird = 0.0f;
        float piePositionAll = 100.0f;

        //Draw a head ballance
        ballanceMoney.set("$ " + String.valueOf(formatDoubleZero.format(sumAllListCoin)));

        //Draw a head percent %percentFirs
        if (listItem.size() != 0 && maxElemFirs != 0.0) {
            percentFirst.set(String.valueOf(formatQuadrupleZero.format(maxElemFirs / onePercent)) + "%");
            piePositionFirst = (float) (maxElemFirs / onePercent);
        }

        //Draw a head percent %percentFirs
        if (listItem.size() != 0 && maxElemSecond != 0.0) {
            percentSecond.set(String.valueOf(formatQuadrupleZero.format(maxElemSecond / onePercent)) + "%");
            piePositionSecond = (float) (maxElemSecond / onePercent);
        }

        //Draw a head percent %percentFirs
        if (listItem.size() != 0 && maxElemThird != 0.0) {
            percentThird.set(String.valueOf(formatQuadrupleZero.format(maxElemThird / onePercent)) + "%");
            piePositionThird = (float) (maxElemThird / onePercent);
        }

        //Draw a head percent %percentFirs
        if (listItem.size() != 0 && sumAllListCoin != 0.0) {
            percentFourth.set(String.valueOf(formatQuadrupleZero.format((sumAllListCoin - maxElemFirs - maxElemSecond - maxElemThird) / onePercent)) + "%");
            piePositionAll = (float) ((sumAllListCoin - maxElemFirs - maxElemSecond - maxElemThird) / onePercent);
        }


        //Draw PieView
        float[] piePosition = {piePositionFirst, piePositionSecond, piePositionThird, piePositionAll};
        PieView.setPercent(piePosition);
    }


}
