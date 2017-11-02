package com.ximsfei.skindemo.window;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.ximsfei.skindemo.R;

public class WindowService extends Service implements View.OnClickListener {

    private WindowManager wManager;
    private WindowManager.LayoutParams mParams;
    private TextView textView;
    private boolean flag = true;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        wManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        mParams = new WindowManager.LayoutParams();
        mParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        mParams.format = PixelFormat.TRANSLUCENT;
        mParams.flags |= WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        mParams.width = 490;
        mParams.height = 160;
        mParams.x = 0;
        mParams.y = 0;
        textView = (TextView) LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_t, null);
        textView.setText("WindowManager add View");
        textView.setOnClickListener(this);
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (flag) {
            flag = false;
            wManager.addView(textView, mParams);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        if (textView.getParent() != null) {
            wManager.removeView(textView);
        }
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        if (v.equals(textView)) {
            flag = true;
            if (textView.getParent() != null) {
                wManager.removeView(textView);
            }
        }
    }
}