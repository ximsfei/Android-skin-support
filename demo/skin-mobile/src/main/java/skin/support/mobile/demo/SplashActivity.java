package skin.support.mobile.demo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import skin.support.mobile.demo.activity.MainActivity;
import skin.support.mobile.demo.activity.base.BaseActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        TextView buildTimeTv = findViewById(R.id.tv_build_time);
        buildTimeTv.setText(String.format(getString(R.string.splash_build_time), BuildConfig.BUILD_TIME));
        buildTimeTv.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}
