package com.zsl.test.basezbproject.http;


import io.reactivex.Observer;

/**
 * Created by mac1010 on 2018/3/20.
 */

public abstract class BaseObserver<T> implements Observer<T> {

    @Override
    public void onError(Throwable e) {
        onMyError(e);
    }

    @Override
    public void onNext(T t) {
        onMyNext(t);
    }


    public abstract void  onMyError(Throwable e);
    public abstract void  onMyNext(T t);

}
