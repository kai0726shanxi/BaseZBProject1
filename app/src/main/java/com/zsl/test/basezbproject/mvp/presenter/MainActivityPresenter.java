package com.zsl.test.basezbproject.mvp.presenter;

import com.zsl.test.basezbproject.App;
import com.zsl.test.basezbproject.mvp.base.BasePresenter;
import com.zsl.test.basezbproject.mvp.view.IMainActivityView;

/**
 * @author Created by stone
 * @since 2018/7/11
 */

public class MainActivityPresenter extends BasePresenter<IMainActivityView> {
    public MainActivityPresenter(App app) {
        super(app);
    }
}
