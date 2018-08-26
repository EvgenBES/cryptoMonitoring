package com.test.presentation.screeens.list.fragment.right;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.test.com.testproject.R;
import android.widget.ImageView;

import com.test.domain.entity.Coin;
import com.test.presentation.base.recycler.BaseItemViewModel;
import com.test.presentation.base.recycler.ClickedItemModel;

import java.text.DecimalFormat;

import io.reactivex.subjects.PublishSubject;

public class RightItemFragmentModel extends BaseItemViewModel<Coin> {

    public ObservableField<String> price = new ObservableField<>("");
    public ObservableField<String> name = new ObservableField<>("");
    public ObservableField<String> symbol = new ObservableField<>("");
    public ObservableField<Integer> imageRes = new ObservableField<>();

    PublishSubject<ClickedItemModel<Coin>> buttonOneClickSubject;




    @BindingAdapter({"android:src"})
    public static void setImageViewResource(ImageView imageView, int imageRes) {
        imageView.setImageResource(imageRes);
    }


    //два вида формата для отображения цены
    private DecimalFormat formatDoubleZero = new DecimalFormat("##.00");
    private DecimalFormat formatQuadrupleZero = new DecimalFormat("#0.000");
    private Coin coin;
    private int position = 0;

    //этот конструктор не обязательно, это для специфических кликах
    //например когда внутри item есть две кнопки и нужно обрабатывать клики на них
    public RightItemFragmentModel(PublishSubject<ClickedItemModel<Coin>> buttonOneClickSubject) {
        this.buttonOneClickSubject = buttonOneClickSubject;
    }

    @Override
    public void setItem(Coin coin, int position) {
        this.coin = coin;
        if (coin.getPrice() > 100) {
            price.set(String.valueOf(formatDoubleZero.format(coin.getPrice())) + "$");
        } else {
            price.set(String.valueOf(formatQuadrupleZero.format(coin.getPrice())) + "$");
        }
        name.set(coin.getName());
        symbol.set(coin.getSymbol());



        imageRes.set(R.drawable.btc);
    }


    //этот метод не обязательно, это для специфических кликах
    //например когда внутри item есть две кнопки и нужно обрабатывать клики на них
    public void onMyButtonOneClicked() {
        buttonOneClickSubject.onNext(new ClickedItemModel(coin, position));
    }




}
