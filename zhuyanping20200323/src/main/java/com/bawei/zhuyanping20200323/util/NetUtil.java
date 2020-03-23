package com.bawei.zhuyanping20200323.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetUtil {
    Handler handler = new Handler();
    //单例
    private NetUtil(){

    }
    public static NetUtil getInstance(){
        return NETUTIL;
    }
    public static final NetUtil NETUTIL = new NetUtil();
    //流转字符串
    public String inToString(InputStream inputStream) throws Exception {
        //读写三件套
        byte[] bytes = new byte[1024];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int len= -1;
        while ((len = inputStream.read(bytes))!= -1){
            byteArrayOutputStream.write(bytes,0,len);
        }
        byte[] bytes1 = byteArrayOutputStream.toByteArray();
        String json = new String(bytes1);
        return json;
    }
    //流转图片
    public Bitmap intoBitmap(InputStream inputStream){
        return BitmapFactory.decodeStream(inputStream);
    }
    //doget
    public void doGet(final String httpurl, final MyCall myCall){
        new Thread(new Runnable() {
            @Override
            public void run() {
                InputStream inputStream = null;
                try {
                    URL url = new URL(httpurl);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setConnectTimeout(5000);
                    httpURLConnection.setReadTimeout(5000);
                    httpURLConnection.connect();
                    //有网络 判断成功
                    if (httpURLConnection.getResponseCode()==200){
                        inputStream = httpURLConnection.getInputStream();
                        final String json = inToString(inputStream);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Log.i("TAG", "请求成功"+json);
                                myCall.doGetSu(json);
                            }
                        });
                    }else {
                        Log.i("TAG", "失败");
                        myCall.doGetEr();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.i("TAG", "失败");
                    myCall.doGetEr();
                }finally {
                    if (inputStream==null){
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();

    }
    //dogetpoho
    public void doGetpoho(final String httpurl, final ImageView imageView){
        new Thread(new Runnable() {
            @Override
            public void run() {
                InputStream inputStream = null;
                try {
                    URL url = new URL(httpurl);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setConnectTimeout(5000);
                    httpURLConnection.setReadTimeout(5000);
                    httpURLConnection.connect();
                    //有网络 判断成功
                    if (httpURLConnection.getResponseCode()==200){
                        inputStream = httpURLConnection.getInputStream();
                        final Bitmap bitmap = intoBitmap(inputStream);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Log.i("TAG", "请求成功");
                                imageView.setImageBitmap(bitmap);
                            }
                        });
                    }else {
                        Log.i("TAG", "失败");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.i("TAG", "失败");
                }finally {
                    if (inputStream==null){
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();

    }
    //网络请求  是否有网
    public boolean Hannet(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo.isAvailable()&&activeNetworkInfo!=null){
            return true;
        }else{
            return false;
        }
    }


    //接口
    public interface MyCall{
        void doGetSu(String json);
        void doGetEr();
    }
}
