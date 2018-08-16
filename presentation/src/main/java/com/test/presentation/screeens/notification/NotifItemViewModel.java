package com.test.presentation.screeens.notification;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.widget.ImageView;

import com.test.domain.entity.Coin;
import com.test.executor.LoadImagePicaso;
import com.test.presentation.base.recycler.BaseItemViewModel;
import com.test.presentation.base.recycler.ClickedItemModel;

import io.reactivex.subjects.PublishSubject;

public class NotifItemViewModel extends BaseItemViewModel<Coin> {

    public ObservableField<String> name = new ObservableField<>("");
    public ObservableField<String> pricePosition = new ObservableField<>("");
    public ObservableField<String> imageUrl = new ObservableField<>("");
    public ObservableField<Boolean> motionPrice = new ObservableField<>(true);

    PublishSubject<ClickedItemModel> buttonOneClickSubject;


    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        LoadImagePicaso.loaderAvatar(view, imageUrl);
    }

    private int id = 0;
    private Coin coin;

    //обрабатывать клики на них
    public NotifItemViewModel(PublishSubject<ClickedItemModel> buttonOneClickSubject) {
        this.buttonOneClickSubject = buttonOneClickSubject;
    }

    @Override
    public void setItem(Coin coin, int position) {
        this.coin = coin;
        this.id = (int) coin.getId();
        name.set(coin.getName());
        pricePosition.set("$ " + String.valueOf(coin.getPricePosition()));
        imageUrl.set(coin.getImage());
        motionPrice.set(coin.getMotionPrice());
    }


    //внутри item есть кнопки и нужно обрабатывать клики на них
    public void onMyButtonOneClicked() {
        buttonOneClickSubject.onNext(new ClickedItemModel(coin, id));
    }

}
