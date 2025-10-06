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
                String verify = CyberActivity.submitData(MainActivity.this);
                if (verify.equals("")){

                }
                else if (verify.equals("true")){
                    reset();
                }else {
                }
                
            }
        });

        reset();

    }

    private void reset() {
        CyberActivity.dataPass("Iq2XWnxk2v8fyzv9Qz71JPXMdUNsRPMk", "YNIKcMM9O9NxfOAaXXEl193WV5FTgQxl",
                "com.cybersiara.app", MainActivity.this);
    }
}