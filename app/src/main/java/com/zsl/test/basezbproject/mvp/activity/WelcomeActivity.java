package com.zsl.test.basezbproject.mvp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.zsl.test.basezbproject.MainActivity;
import com.zsl.test.basezbproject.R;
import com.zsl.test.basezbproject.utils.StatusBarUtil;

public class WelcomeActivity extends AppCompatActivity {
    private Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setTranslucentDiff(WelcomeActivity.this);
        setContentView(R.layout.activity_welcome);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
               startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                finish();
            }
        },3000);



    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
