package com.zsl.test.basezbproject.mvp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zsl.test.basezbproject.R;
import com.zsl.test.basezbproject.mvp.base.BaseFragment;
import com.zsl.test.basezbproject.mvp.presenter.home.HomeFragmentPresenter;
import com.zsl.test.basezbproject.mvp.view.home.IHomefragmentView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Created by stone
 * @since 2018/7/11
 */

public class HomeFragment extends BaseFragment<IHomefragmentView, HomeFragmentPresenter> implements IHomefragmentView {


    @BindView(R.id.iv_img)
    ImageView ivImg;
    Unbinder unbinder;

    public static HomeFragment newInstances() {


        return new HomeFragment();
    }


    @Override
    public int getRootViewId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initUI() {
        ivImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startWeb("我的百度","http://www.baidu.com");
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public HomeFragmentPresenter createPresenter() {
        return new HomeFragmentPresenter(getApp());
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
