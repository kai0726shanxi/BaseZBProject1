package com.zsl.test.basezbproject.mvp.presenter.home;

import com.zsl.test.basezbproject.App;
import com.zsl.test.basezbproject.mvp.base.BasePresenter;
import com.zsl.test.basezbproject.mvp.view.home.IHomefragmentView;

/**
 * @author Created by stone
 * @since 2018/7/11
 */

public class HomeFragmentPresenter extends BasePresenter<IHomefragmentView> {
    public HomeFragmentPresenter(App app) {
        super(app);
    }
}
