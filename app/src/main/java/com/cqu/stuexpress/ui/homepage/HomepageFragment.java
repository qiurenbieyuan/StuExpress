/**
 * 发现
 */
package com.cqu.stuexpress.ui.homepage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cqu.stuexpress.R;
import com.cqu.stuexpress.utils.CommonTools;
import com.cqu.stuexpress.utils.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

public class HomepageFragment extends Fragment implements View.OnClickListener {
    private View view;
    private CommonTools myTool;
    private Banner mBanner;
    // 服务器获取
    List<String> images;
    List<String> titles;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_homepage, container, false);
        initView(view);
        initData();
        initBanner();
        initEvent();

        return view;
    }

    private void initData() {
        images = new ArrayList<>();
        images.add("http://graduate.cqu.edu.cn/images/slide1.jpg");
        images.add("http://graduate.cqu.edu.cn/images/slide3.jpg");
        images.add("http://graduate.cqu.edu.cn/images/slide2.jpg");

        titles = new ArrayList<>();
        titles.add("一滴水也可以看到一个大世界");
        titles.add("生气勃勃的五教");
        titles.add("最美不过校园一角");
    }

    private void initBanner() {
        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE)
                .setImageLoader(new GlideImageLoader())
                .setImages(images)
                .setBannerAnimation(Transformer.ZoomOut)
                .setBannerTitles(titles)
                .isAutoPlay(true)
                .setDelayTime(3000)
                .setIndicatorGravity(BannerConfig.RIGHT)
                .start();
    }

    private void initEvent() {
    }

    private void initView(View v) {
        myTool = new CommonTools(getActivity());
        mBanner = (Banner) v.findViewById(R.id.banner);
    }

    @Override
    public void onStart() {
        super.onStart();
        mBanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        mBanner.stopAutoPlay();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }
    }
}
