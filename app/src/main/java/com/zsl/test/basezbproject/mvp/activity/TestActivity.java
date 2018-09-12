package com.zsl.test.basezbproject.mvp.activity;

import android.support.annotation.NonNull;

import com.zsl.test.basezbproject.R;
import com.zsl.test.basezbproject.mvp.base.BaseActivity;
import com.zsl.test.basezbproject.mvp.presenter.TestActivityPresenter;
import com.zsl.test.basezbproject.mvp.view.ITestActivityView;
import com.zsl.test.basezbproject.utils.StatusBarUtil;

public class TestActivity extends BaseActivity<ITestActivityView,TestActivityPresenter> implements ITestActivityView{

    @NonNull
    @Override
    public TestActivityPresenter createPresenter() {
        return new TestActivityPresenter(getApp());
    }

    @Override
    public int getRootViewId() {

        return R.layout.activity_test;
    }

    @Override
    public void initUI() {
        StatusBarUtil.setColor(TestActivity.this, getResources().getColor(R.color.colorAccent));

        getPresenter().getBanner();
    }

    @Override
    public void initData() {

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
}
