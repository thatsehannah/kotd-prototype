package com.example.elliothannahiii.kotd;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.Random;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PickShoeActivity extends BaseActivity {

    @BindView(R.id.weather_icon) ImageView weatherIcon;

    @OnClick(R.id.pick_home)
    public void backToHome(){
        toActivity(MainActivity.class);
    }

    private Animation alphaAnimation;
    final int sunny = 0;
    final int rain = 1;
    final int snow = 2;
    final int hot = 3;
    final int cold = 4;
    private int randomWeather;
    public static String weatherForToday;
    Random randomNum = new Random();

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            String s = bundle.getString("msg");
            switch (msg.what){
                case sunny:
                    toastLong("Looks Like "+s);
                    break;
                case rain:
                    toastLong("Looks Like " +s);
                    break;
                case snow:
                    toastLong("Looks Like " +s);
                    break;
                case hot:
                    toastLong("Looks Like It " +s);
                    break;
                case cold:
                    toastLong("It Will Be Cold Today! "+s);
                    break;
                default:
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_shoe);
        ButterKnife.bind(this);
        alphaAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha);
        fakeProgress();
        toLastActivity();
    }

    private void toLastActivity() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toActivity(YourShoeForToday.class);
            }
        }, 9000);
    }

    public void fakeProgress(){
        final int maxProgress = 100;
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setProgress(0);
        pd.setTitle("Getting The Weather for Today...");
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setMax(maxProgress);
        pd.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                randomWeather = randomNum.nextInt(5);
                if (randomWeather == 0){
                    weatherIcon.setImageResource(R.drawable.kotd_sunny);
                    weatherForToday = "Sunny";
                }else if (randomWeather == 1){
                    weatherIcon.setImageResource(R.drawable.kotd_rain);
                    weatherForToday = "Rain";
                }else if (randomWeather == 2){
                    weatherIcon.setImageResource(R.drawable.kotd_snow);
                    weatherForToday = "Snow";
                }else if (randomWeather == 3){
                    weatherIcon.setImageResource(R.drawable.kotd_hot);
                    weatherForToday = "Hot";
                }else if (randomWeather == 4){
                    weatherIcon.setImageResource(R.drawable.kotd_cold);
                    weatherForToday = "Cold";

                }
                weatherIcon.setVisibility(View.VISIBLE);
                weatherIcon.startAnimation(alphaAnimation);
                int progress = 0;
                while (progress < maxProgress){
                    try {
                        Thread.sleep(40);
                        progress++;
                        pd.setProgress(progress);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }

                Bundle bundle = new Bundle();
                Message msg = Message.obtain();
                if (randomWeather == 0){
                    msg.what = sunny;
                    bundle.putString("msg", "The Sun Will Shine Today!");
                }else if (randomWeather == 1){
                    msg.what = rain;
                    bundle.putString("msg", "There Will Be Rain!");
                } else if (randomWeather == 2){
                    msg.what = snow;
                    bundle.putString("msg", "Snow Will Fall Today!");
                }else if (randomWeather == 3){
                    msg.what = hot;
                    bundle.putString("msg", "Will Be Extra Hot Today!");
                }else if (randomWeather == 4){
                    msg.what = cold;
                    bundle.putString("msg", "Hope Your Shoes Are Warm!");
                }
                msg.setData(bundle);
                mHandler.sendMessage(msg);
                pd.cancel();
            }


        }).start();

    }
}
