package com.mertkilic.countries.adapter;

import android.content.res.Resources;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by Mert Kilic on 5.3.2017.
 */

public class DataBindingAdapter {

    @BindingAdapter("flag")
    public static void setFlag(ImageView view, String countryCode) {
        Resources resources = view.getContext().getResources();

        if (countryCode.equals("do")) //because of IDE doesnt allow to name png file as "do.png"
            countryCode = "dom";

        int resourceId = resources.getIdentifier(countryCode.toLowerCase(), "drawable",
                view.getContext().getPackageName());
        if (resourceId != 0)
            Picasso.with(view.getContext()).load(resourceId).into(view);
    }

    @BindingAdapter("image")
    public static void setImage(ImageView view, String url) {
        if(url != null){
            Picasso.with(view.getContext()).load(url).into(view);
        }
    }
}
