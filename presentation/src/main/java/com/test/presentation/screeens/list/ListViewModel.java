package com.test.presentation.screeens.list;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.test.com.testproject.R;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;

import com.test.app.App;
import com.test.domain.entity.Coin;
import com.test.presentation.base.BaseViewModel;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.subjects.PublishSubject;

public class ListViewModel extends BaseViewModel<ListRouter, Coin> {

    public ObservableInt titelText = new ObservableInt(View.VISIBLE);
    public ObservableInt searchText = new ObservableInt(View.GONE);
    public ObservableInt imageSearch = new ObservableInt(R.drawable.ic_search);
    public ObservableField<String> textSearch = new ObservableField<>();

    private Animation animRotate = AnimationUtils.loadAnimation(App.getContext(), R.anim.rotate);
    private Animation animScale = AnimationUtils.loadAnimation(App.getContext(), R.anim.scale);

    private boolean statusSearch = true;


    @BindingAdapter({"android:src"})
    public static void setImageViewResource(ImageView imageView, int resource) {
        imageView.setImageResource(resource);
    }

    @Override
    protected void runInject() {
        App.getAppComponent().runInject(this);
    }

    public void onClickBack() {
        router.getActivity().finish();
    }

    @Override
    public void setItem(Coin coin) {

    }

    public ListViewModel() {
    }

    public void onClickSearch() {
        ImageView imageViewSearch = router.getActivity().findViewById(R.id.image_search);
        if (statusSearch) {
            statusSearch = false;
            imageViewSearch.setAnimation(animRotate);
            titelText.set(View.GONE);
            searchText.set(View.VISIBLE);
            imageSearch.set(R.drawable.ic_close);
        } else {
            statusSearch = true;
            imageViewSearch.setAnimation(animScale);
            titelText.set(View.VISIBLE);
            searchText.set(View.GONE);
            imageSearch.set(R.drawable.ic_search);
            textSearch.set("");

        }
    }
}
