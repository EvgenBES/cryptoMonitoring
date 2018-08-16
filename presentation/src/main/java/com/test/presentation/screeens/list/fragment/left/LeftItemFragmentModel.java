package com.test.presentation.screeens.list.fragment.left;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.util.Log;
import android.widget.ImageView;

import com.test.domain.entity.Coin;
import com.test.executor.LoadImagePicaso;
import com.test.presentation.base.recycler.BaseItemViewModel;
import com.test.presentation.base.recycler.ClickedItemModel;

import java.text.DecimalFormat;

import io.reactivex.subjects.PublishSubject;

public class LeftItemFragmentModel extends BaseItemViewModel<Coin> {

    public ObservableField<String> price = new ObservableField<>("");
    public ObservableField<String> name = new ObservableField<>("");
    public ObservableField<String> symbol = new ObservableField<>("");
    public ObservableField<String> quantity = new ObservableField<>("");
    public ObservableField<String> imageUrl = new ObservableField<>("");

    PublishSubject<ClickedItemModel> buttonOneClickSubject;


    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        LoadImagePicaso.loaderAvatar(view, imageUrl);
    }

    //два вида формата для отображения цены
    private DecimalFormat formatDoubleZero = new DecimalFormat("##.00");
    private DecimalFormat formatQuadrupleZero = new DecimalFormat("#0.000");

    //этот конструктор не обязательно, это для специфических кликах
    //например когда внутри item есть две кнопки и нужно обрабатывать клики на них
    public LeftItemFragmentModel(PublishSubject<ClickedItemModel> buttonOneClickSubject) {
        this.buttonOneClickSubject = buttonOneClickSubject;
    }

    private Coin coin;
    private int id = 0;

    @Override
    public void setItem(Coin coin, int position) {
        this.coin = coin;
        this.id = (int) coin.getId();
        name.set(coin.getName());
        if ((coin.getPrice() * coin.getQuantity()) > 100) {
            price.set(String.valueOf(formatDoubleZero.format(coin.getPrice() * coin.getQuantity())) + "$");
        } else {
            price.set(String.valueOf(formatQuadrupleZero.format(coin.getPrice() * coin.getQuantity())) + "$");
        }
        symbol.set(coin.getSymbol());
        quantity.set(String.valueOf(formatQuadrupleZero.format(coin.getQuantity())));
        imageUrl.set(coin.getImage());
    }


    //этот метод не обязательно, это для специфических кликах
    //например когда внутри item есть две кнопки и нужно обрабатывать клики на них
    public void onMyButtonOneClicked() {
        buttonOneClickSubject.onNext(new ClickedItemModel(coin, id));
    }
}
