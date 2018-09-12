package com.zsl.test.basezbproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.zsl.test.basezbproject.mvp.base.BaseActivity;
import com.zsl.test.basezbproject.mvp.fragment.HomeFragment;
import com.zsl.test.basezbproject.mvp.presenter.MainActivityPresenter;
import com.zsl.test.basezbproject.mvp.view.IMainActivityView;
import com.zsl.test.basezbproject.utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 测试
 */

public class MainActivity extends BaseActivity<IMainActivityView, MainActivityPresenter> implements IMainActivityView {

    @BindView(R.id.fragmentContent)
    FrameLayout fragmentContent;
    @BindView(R.id.rbHome)
    RadioButton rbHome;
    @BindView(R.id.rbFoucs)
    RadioButton rbFoucs;
    @BindView(R.id.rbLivespecial)
    RadioButton rbLivespecial;
    @BindView(R.id.rbMessage)
    RadioButton rbMessage;
    @BindView(R.id.rbMe)
    RadioButton rbMe;
    @BindView(R.id.rg)
    RadioGroup Rg;
    private HomeFragment homeFragment;
    @Override
    public MainActivityPresenter createPresenter() {
        StatusBarUtil.setColor(MainActivity.this, getResources().getColor(R.color.bottom_bar_text_select),60);
        return new MainActivityPresenter(getApp());
    }

    @Override
    public int getRootViewId() {

        return R.layout.activity_main;
    }
    @Override
    public void initUI() {
         showHome();
      /*  Rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rbHome:
                        showHome();
                        break;
                    case R.id.rbFoucs:
                        //showFocus();
                        break;
                    case R.id.rbMe:
                        //showMe();
                        break;
                    case R.id.rbLivespecial:

                      //  showLiveSpecial();
                        break;
                    case R.id.rbMessage:

                       // showMessage();
                        break;
                }
            }
        });*/
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        hidefragment(fragmentTransaction, homeFragment);



    }

    private void hidefragment(FragmentTransaction fragmentTransaction, Fragment fragment) {
        if (fragment != null) {
            fragmentTransaction.hide(fragment);
        }
    }

    private void showFragment(FragmentTransaction fragmentTransaction, Fragment fragment) {
        fragmentTransaction.show(fragment).commit();
    }
    /**
     * 显示首页
     */
    private void showHome() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hideAllFragment(fragmentTransaction);
        if (homeFragment == null) {
            homeFragment = HomeFragment.newInstances();
            fragmentTransaction.add(R.id.fragmentContent, homeFragment);
        }
        showFragment(fragmentTransaction, homeFragment);
    }
}
