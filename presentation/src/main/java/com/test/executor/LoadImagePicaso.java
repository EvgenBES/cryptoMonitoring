package com.test.executor;

import android.test.com.testproject.R;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class LoadImagePicaso {
    public static void loaderAvatar(ImageView view, String imageUrl) {
        Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.preloader)
                .error(R.drawable.preloader)
//                .transform(new CircularTransformation()) округление картинки
                .into(view);
    }
}
