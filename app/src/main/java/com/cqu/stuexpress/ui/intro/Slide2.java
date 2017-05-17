package com.cqu.stuexpress.ui.intro;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cqu.stuexpress.R;
import com.github.paolorotolo.appintro.ISlideBackgroundColorHolder;

public class Slide2 extends Fragment implements ISlideBackgroundColorHolder {
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_slide2, container, false);
        return view;
    }

    @Override
    public int getDefaultBackgroundColor() {
        return Color.parseColor("#f99401");
    }

    @Override
    public void setBackgroundColor(@ColorInt int backgroundColor) {
        // Set the background color of the view within your slide to which the transition should be applied.
        if (view != null) {
            view.setBackgroundColor(backgroundColor);
        }
    }
}
