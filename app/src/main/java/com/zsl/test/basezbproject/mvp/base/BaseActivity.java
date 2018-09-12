package com.zsl.test.basezbproject.mvp.base;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;

import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;
import com.zsl.test.basezbproject.App;
import com.zsl.test.basezbproject.Constants;
import com.zsl.test.basezbproject.customview.ErrorDialogFragment;
import com.zsl.test.basezbproject.mvp.activity.ContentActivity;
import com.zsl.test.basezbproject.utils.StatusBarUtil;

import java.util.List;

import butterknife.ButterKnife;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * @since 2017/2/20
 */

public abstract class BaseActivity<V extends MvpView, P extends MvpPresenter<V>> extends MvpActivity<V,P> {

    //错误消息弹窗
    private ErrorDialogFragment mErrDlgFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getRootViewId());
        initUI();
        initData();
        mErrDlgFragment = new ErrorDialogFragment();

    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }


    public <T> void  toSetList(List<T> list, List<T> newList, boolean isMore){

        if(list!=null && newList!=null){
            synchronized (BaseFragment.class){
                if(!isMore){
                    list.clear();
                }
                list.addAll(newList);
            }
        }
    }


    protected Intent getActivityIntent(int fragmentKey) {
        Intent intent = getContentActivityIntent();
        intent.putExtra(Constants.KEY_FRAGMENT, fragmentKey);
        return intent;
    }
    protected Intent getContentActivityIntent() {
        return new Intent(this, ContentActivity.class);
    }

    public App getApp(){
        return (App)getApplication();
    }
    public abstract int getRootViewId();

    public abstract void initUI();

    public abstract void initData();

    protected void showErrorAndQuit(String errorMsg) {

        if (!mErrDlgFragment.isAdded() && !this.isFinishing()) {
            Bundle args = new Bundle();
            args.putString("errorMsg", errorMsg);
            mErrDlgFragment.setArguments(args);
            mErrDlgFragment.setCancelable(false);

            //此处不使用用.show(...)的方式加载dialogfragment，避免IllegalStateException
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.add(mErrDlgFragment, "loading");
            transaction.commitAllowingStateLoss();
        }
    }



}
