package com.zsl.test.basezbproject.mvp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zsl.test.basezbproject.R;
import com.zsl.test.basezbproject.utils.WebViewUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Created by stone
 * @since 2018/7/11
 */


public class WebFragment extends SimpleFragment {
    @BindView(R.id.progressbar_preview)
    ProgressBar progressbarPreview;
    @BindView(R.id.webview_contract)
    WebViewUtils webviewContract;
    @BindView(R.id.dialog_layout)
    LinearLayout dialogLayout;
    Unbinder unbinder;
    @BindView(R.id.tv_left)
    TextView tvLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_delete)
    ImageView ivDelete;
    private String mtitle;
    private String murl;
    private String mtype;

    public static WebFragment newInstances(String title, String url) {

        WebFragment webFragment = new WebFragment();

        webFragment.mtitle = title;
        webFragment.murl = url;
        return webFragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_web;
    }

    @Override
    public void initUI() {

        tvLeft.setVisibility(View.VISIBLE);
        tvLeft.setText("返回");
        tvTitle.setText(mtitle);
        if (murl!=null&&!murl.equals("")){
            webviewContract.initWebView(progressbarPreview, murl, true);
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
