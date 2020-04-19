package vn.mrlongg71.vnfood.src.utils;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import vn.mrlongg71.vnfood.R;
import vn.mrlongg71.vnfood.src.module.login.view.LoginActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);

        setContentView(R.layout.activity_splash_screen);
//        APIVnFood.init(getApplicationContext());
        findViewById(R.id.btnLoginSplash).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.btnLoginSplash).setClickable(false);
                startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
                finish();

            }
        });
    }
}
