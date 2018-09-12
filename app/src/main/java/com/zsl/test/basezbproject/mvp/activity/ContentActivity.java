package com.zsl.test.basezbproject.mvp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.zsl.test.basezbproject.Constants;
import com.zsl.test.basezbproject.R;
import com.zsl.test.basezbproject.mvp.fragment.WebFragment;
import com.zsl.test.basezbproject.utils.StatusBarUtil;

public class ContentActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setColor(ContentActivity.this, getResources().getColor(R.color.bottom_bar_text_select), 60);
        setContentView(R.layout.activity_content);
        swichFragment(getIntent());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    public void swichFragment(Intent intent) {

        int fragmentKey = intent.getIntExtra(Constants.KEY_FRAGMENT, 0);
        switch (fragmentKey) {
            case Constants.LOGIN_FRAGMENT:


                break;
            case Constants.WEB_FRAGMENT:

                String webtitle = intent.getStringExtra(Constants.KEY_TITLE);
                String weburl = intent.getStringExtra(Constants.KEY_URL);
                replaceFragment(WebFragment.newInstances(webtitle, weburl));
                break;

            default:
                // LogUtils.d("Not found fragment:" + Integer.toHexString(fragmentKey));
                break;
        }
    }


    public void replaceFragment(Fragment fragmnet) {
        replaceFragment(R.id.fragmentContent, fragmnet);
    }

    public void replaceFragment(@IdRes int id, Fragment fragment) {

        getSupportFragmentManager().beginTransaction().replace(id, fragment).commit();
    }

    public static void start(Activity activity, int KEY) {
        Intent intent = new Intent(activity, ContentActivity.class);
        intent.putExtra(Constants.KEY_FRAGMENT, KEY);
        activity.startActivity(intent);
    }

}
