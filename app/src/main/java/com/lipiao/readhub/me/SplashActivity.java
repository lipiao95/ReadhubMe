package com.lipiao.readhub.me;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

/**
 * @author lipiao
 * @data 2018/5/24.
 * @description 描述
 */
public class SplashActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Observable.timer(2, TimeUnit.SECONDS).subscribe(aLong -> {
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                }
        );
    }
}
