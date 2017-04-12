package com.example.elliothannahiii.kotd;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


import butterknife.BindView;
import butterknife.ButterKnife;

public class BarcodeActivity extends BaseActivity {

    @BindView(R.id.redirect) TextView redirection;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode);
        ButterKnife.bind(this);
        ObjectAnimator animator = ObjectAnimator.ofFloat(redirection,"alpha",1,0,1);
        animator.setDuration(2000);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.start();

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toActivity(ShoeNameActivity.class);
            }
        }, 8000);

    }
}
