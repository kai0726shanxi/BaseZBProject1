package com.zsl.test.basezbproject.mvp.base;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.zsl.test.basezbproject.App;
import com.zsl.test.basezbproject.di.componet.AppComponent;

import javax.inject.Inject;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * @since 2017/2/20
 */

public class BasePresenter<V extends BaseView> extends MvpBasePresenter<V> {

    private App app;


    private AppComponent mAppComponent;

    @Inject
    public BasePresenter(App app){
        super();
        this.app = app;
        mAppComponent = app.getmAppComponent();
    }


    public AppComponent getAppComponent(){
        return mAppComponent;
    }


    public App getApp(){
        return getApp();
    }

    @Override
    public boolean isViewAttached() {
        return super.isViewAttached();
    }
}
