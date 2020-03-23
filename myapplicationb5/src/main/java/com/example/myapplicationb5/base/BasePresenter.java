package com.example.myapplicationb5.base;

import android.os.Handler;
import android.os.Message;
import android.webkit.WebResourceRequest;

import androidx.annotation.NonNull;

import com.example.myapplicationb5.bean.Result;

public abstract class BasePresenter {
    private DataCall dataCall;
    public BasePresenter(DataCall dataCall){
        this.dataCall  = dataCall;
    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.obj==null){
                dataCall.fail();
            }else{
                Result result = (Result) msg.obj;
                dataCall.success(result);
            }
        }
    };
    public void request(final Object...args){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Result result = getModel(args);
                Message message = Message.obtain();
                message.obj = result;
                handler.sendMessage(message);
            }
        }).start();
    }

    protected abstract Result getModel(Object...args);
}
