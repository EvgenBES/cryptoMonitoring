package com.test.presentation.screeens.list.fragment.right;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

import com.test.domain.entity.Coin;
import com.test.executor.LoadImagePicaso;
import com.test.presentation.base.recycler.BaseItemViewModel;
import com.test.presentation.base.recycler.ClickedItemModel;

import java.text.DecimalFormat;

import io.reactivex.subjects.PublishSubject;

public class RightItemFragmentModel extends BaseItemViewModel<Coin> {

    public ObservableField<String> price = new ObservableField<>("");
    public ObservableField<String> name = new ObservableField<>("");
    public ObservableField<String> symbol = new ObservableField<>("");
    public ObservableField<String> imageUrl = new ObservableField<>("");

    PublishSubject<ClickedItemModel> buttonOneClickSubject;


    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        LoadImagePicaso.loaderAvatar(view, imageUrl);
    }


    //два вида формата для отображения цены
    private DecimalFormat formatDoubleZero = new DecimalFormat("##.00");
    private DecimalFormat formatQuadrupleZero = new DecimalFormat("#0.000");
    private Coin coin;

    //этот конструктор не обязательно, это для специфических кликах
    //например когда внутри item есть две кнопки и нужно обрабатывать клики на них
    public RightItemFragmentModel(PublishSubject<ClickedItemModel> buttonOneClickSubject) {
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
        imageUrl.set(coin.getImage());
    }


//    //этот метод не обязательно, это для специфических кликах
//    //например когда внутри item есть две кнопки и нужно обрабатывать клики на них
//    public void onMyButtonOneClicked() {
//        buttonOneClickSubject.onNext(new ClickedItemModel(coin, position));
//    }

}
