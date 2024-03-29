package com.cheklibrary;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cybersiara.app.ConnectivityDetector;
import com.cybersiara.app.CyberActivity;
import com.example.swipebutton_library.OnActiveListener;

public class MainActivity extends AppCompatActivity {

    LinearLayout l_main_submit;
    Handler handler =  new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        l_main_submit = (LinearLayout) findViewById(R.id.l_submit_main);
        l_main_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String verify = CyberActivity.checkVerify(MainActivity.this);
                if (verify.equals("true")){
                    Toast.makeText(getApplicationContext(), "Verified", Toast.LENGTH_LONG).show();
                    reset();
                }else {
                    Toast.makeText(getApplicationContext(), "Captcha Not Verified", Toast.LENGTH_LONG).show();
                }
            }
        });

        reset();

        CyberActivity.frame.setTag(CyberActivity.frame.getVisibility());
        CyberActivity.frame.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int newVis = CyberActivity.frame.getVisibility();
                if((int)CyberActivity.frame.getTag() != newVis)
                {
                    CyberActivity.frame.setTag(CyberActivity.frame.getVisibility());
                    if (CyberActivity.frame.getVisibility() == View.VISIBLE){
                        l_main_submit.setVisibility(View.VISIBLE);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                reset();
                                l_main_submit.setVisibility(View.GONE);
                            }
                        }, CyberActivity.time_reset);
                    }else{
                        if (handler != null){
                            handler.removeCallbacksAndMessages(null);
                        }
                        l_main_submit.setVisibility(View.GONE);
                    }
                }
            }
        });

    }

    private void reset() {
        CyberActivity.dataPass("kNKLUO9OyUNd4y1azl7TFFH8hI0zyzYh", "jwqIM6PQ8YVfy9HOgQuRjW6OCyX0dAGS",
                "com.cybersiara.app", MainActivity.this);
    }
}