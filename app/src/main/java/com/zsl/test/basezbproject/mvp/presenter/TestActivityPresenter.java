package com.zsl.test.basezbproject.mvp.presenter;

import com.zsl.test.basezbproject.App;
import com.zsl.test.basezbproject.bean.Banner;
import com.zsl.test.basezbproject.http.HttpResult;
import com.zsl.test.basezbproject.mvp.base.BasePresenter;
import com.zsl.test.basezbproject.mvp.view.ITestActivityView;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Created by stone
 * @since 2018/7/10
 */

public class TestActivityPresenter extends BasePresenter<ITestActivityView> {
    public TestActivityPresenter(App app) {
        super(app);
    }

    //获取轮播
    public void getBanner() {
        if (isViewAttached()) {
            getView().showProgress();
        }
        getAppComponent().getAPIService()
                .getBanner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<List<Banner>>>() {

                    @Override
                    public void onError(Throwable e) {
                        if (isViewAttached())
                            getView().onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HttpResult<List<Banner>> userBeanHttpResult) {

                    }
                });
    }

}
