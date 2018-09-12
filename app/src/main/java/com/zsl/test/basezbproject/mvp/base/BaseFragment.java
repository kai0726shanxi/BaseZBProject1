package com.zsl.test.basezbproject.mvp.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.hannesdorfmann.mosby.mvp.MvpFragment;
import com.zsl.test.basezbproject.App;
import com.zsl.test.basezbproject.Constants;
import com.zsl.test.basezbproject.mvp.activity.ContentActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * @since 2017/2/20
 */

public abstract class BaseFragment<V extends BaseView, P extends BasePresenter<V>> extends MvpFragment<V, P> {

    protected Context context;

    private View rootView;

    private Unbinder mUnbinder;


    private Map<String,String> map = new HashMap<>();
   // private NewUserBean userBean;

    public <T extends View> T findView(@IdRes int id) {
        return (T) rootView.findViewById(id);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        context = getActivity();
        rootView = inflater.inflate(getRootViewId(), container, false);
        mUnbinder = ButterKnife.bind(this, rootView);
        initUI();
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null)
            mUnbinder.unbind();
    }

    public View getRootView() {
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
       // userBean = SpSingleInstance.getSpSingleInstance().userBean;
        initData();
    }

    protected void startWeb(String title, String url) {
        Intent intent = getFragmentIntent(Constants.WEB_FRAGMENT);
        intent.putExtra(Constants.KEY_TITLE, title);
        intent.putExtra(Constants.KEY_URL, url);
        startActivity(intent);
      //  getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_to_right);

    }
    public void replaceFragment(@IdRes int id, Fragment fragment) {
        getFragmentManager().beginTransaction().replace(id, fragment).commit();
    }

    public void replaceChildFragment(@IdRes int id, Fragment fragment) {
        getChildFragmentManager().beginTransaction().replace(id, fragment).commit();
    }

    public App getApp() {
        return (App) getActivity().getApplication();
    }

    public <T> void toSetList(List<T> list, List<T> newList, boolean isMore) {

        if (list != null && newList != null) {
            synchronized (BaseFragment.class) {
                if (!isMore) {
                    list.clear();
                }
                list.addAll(newList);
            }
        }
    }



    //--------------------------------

    protected Intent getIntent() {
        return getActivity().getIntent();
    }


    protected Intent getFragmentIntent(int fragmentKey) {
        Intent intent = getContentActivityIntent();
        intent.putExtra(Constants.KEY_FRAGMENT, fragmentKey);
        return intent;
    }

    protected Intent getContentActivityIntent() {
        return new Intent(context, ContentActivity.class);
    }

    protected void startActivity(Class<?> cls) {
        startActivity(new Intent(context, cls));
    }


    protected void finish() {
        getActivity().finish();
    }








    //--------------------------------


    /**
     * 隐藏软键盘
     *
     * @param v
     */
    public void hideInputMethod(final EditText v) {

        InputMethodManager imm = (InputMethodManager) context.getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        v.clearFocus();
    }

    /**
     * 显示软键盘
     *
     * @param v
     */
    public void showInputMethod(final EditText v) {

        v.requestFocus();
        InputMethodManager imm = (InputMethodManager) context.getApplicationContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT);
    }

    public abstract int getRootViewId();

    public abstract void initUI();

    public abstract void initData();



}
