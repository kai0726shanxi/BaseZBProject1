package com.zsl.test.basezbproject.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class WebViewUtils extends WebView {

    public WebViewUtils(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public WebViewUtils(Context context) {
        super(context);
    }

    public WebViewUtils(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 初始化webview
     *
     * @param url
     * @param openWay:true :在webview打开，false在手机默认浏览器打开
     */
    public void initWebView(final ProgressBar progressBar, final String url, final boolean openWay) {

        this.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    progressBar.setVisibility(View.INVISIBLE);
                } else {
                    if (View.INVISIBLE == progressBar.getVisibility()) {
                        progressBar.setVisibility(View.VISIBLE);
                    }
                    progressBar.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }

        });
        this.post(new Runnable() {
            @Override
            public void run() {
                WebViewUtils.this.loadUrl(url);
            }
        });
        this.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                view.loadUrl(url);
                return openWay;
            }


            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });

    }
}