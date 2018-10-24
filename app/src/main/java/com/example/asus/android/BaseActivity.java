package com.example.asus.android;

import android.os.Bundle;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.example.asus.android.HttpPostTask;

import android.os.Handler;

public class BaseActivity extends AppCompatActivity  {

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected  void sendHttpPostRequest(String url, CommonRequest request, ResponseHandler responseHandler){

      //  android.os.Handler mHandler;
        new HttpPostTask(request,mHandler,responseHandler).execute(url);


    }
    protected Handler mHandler = new Handler(){
        public void handleMessage(Message msg){
            super.handleMessage(msg);
       //     Constant.

        }
    };
}
