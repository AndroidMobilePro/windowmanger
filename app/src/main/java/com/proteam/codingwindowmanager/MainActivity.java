package com.proteam.codingwindowmanager;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ContentFrameLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    MyService mBoundService;
    boolean mServiceBound = false;
    private View view;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        view = inflater.inflate(R.layout.activity_main, null, false);
//        view = getWindow().getDecorView().getRootView();

        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyService.class);
                startService(intent);
                bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
            }
        });
        findViewById(R.id.btnClick).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Click", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mServiceBound = false;
        }

        @SuppressLint("ResourceAsColor")
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.MyBinder myBinder = (MyService.MyBinder) service;
            mBoundService = myBinder.getService();
            mServiceBound = true;

            ContentFrameLayout root = getWindow().getDecorView().findViewById(android.R.id.content);
//            View rootView = getWindow().getDecorView().findViewById(android.R.id.content);
            final android.view.ViewParent parent = root.getParent ();

//            if (parent instanceof android.view.ViewManager)
//            {
//                final android.view.ViewManager viewManager = (android.view.ViewManager) parent;
//
//                viewManager.removeView(root);
//            }
            mBoundService.initView(root);

        }
    };

    @Override
    protected void onPause() {
        super.onPause();
//        if (mBoundService!=null) {
//            mBoundService.removeView();
//            finish();
//        }
    }
}
