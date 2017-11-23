package com.github.caoyouxin.taoke.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.caoyouxin.taoke.R;
import com.github.caoyouxin.taoke.api.TaoKeApi;
import com.github.caoyouxin.taoke.model.UserData;
import com.github.gnastnosaj.boilerplate.ui.activity.BaseActivity;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;


public class SplashActivity extends BaseActivity {

    @BindView(R.id.splash)
    View splash;

    @BindView(R.id.sign_up)
    Button signUp;

    @BindView(R.id.sign_in)
    Button signIn;

    @BindView(R.id.copyright)
    TextView copyright;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        ButterKnife.bind(this);

        splash.animate().scaleX(1.2f).scaleY(1.2f).setDuration(1500);
        copyright.animate().alpha(1).setDuration(1500);

        if (UserData.restore()) {
            Observable.timer(3, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(aLong -> {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
                if (sharedPreferences.getBoolean(IntroActivity.INTRO_READ, false)) {
                    startActivity(new Intent(this, TaoKeActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                } else {
                    startActivity(new Intent(this, IntroActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                }
            });
        } else {
            signUp.animate().alpha(1).setDuration(1500);
            signIn.animate().alpha(1).setDuration(1500);
        }
    }

    @OnClick({R.id.sign_in, R.id.sign_up})
    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_in:
                startActivity(new Intent(this, SignInActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                break;
            case R.id.sign_up:
                startActivity(new Intent(this, SignUpActivity.class));
                break;
        }
    }
}
