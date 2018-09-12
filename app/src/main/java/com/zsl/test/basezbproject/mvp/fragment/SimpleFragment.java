package com.zsl.test.basezbproject.mvp.fragment;

import com.zsl.test.basezbproject.mvp.base.BaseFragment;
import com.zsl.test.basezbproject.mvp.base.BasePresenter;
import com.zsl.test.basezbproject.mvp.base.BaseView;

public abstract class SimpleFragment extends BaseFragment<BaseView, BasePresenter<BaseView>> {

    @Override
    public BasePresenter<BaseView> createPresenter() {
        return new BasePresenter<>(getApp());
    }


}