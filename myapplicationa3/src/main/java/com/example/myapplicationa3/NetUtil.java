package com.example.myapplicationa3;

import android.content.res.Resources;
import android.os.Handler;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class NetUtil {
    Handler handler = new Handler();
    //单例
    private NetUtil(){
    }
    private static final NetUtil getInstance(){
        return NETUTIL;
    }
    private static final NetUtil NETUTIL = new NetUtil();
    //流转字符串
    public String inToString(InputStream inputStream) throws Exception {
        String json = "";
        //读写三件套
        byte[] bytes = new byte[1024];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int len =-1;
        while ((len = inputStream.read(bytes))!=-1){
            byteArrayOutputStream.write(bytes,0,len);
        }
        byte[] bytes1 = byteArrayOutputStream.toByteArray();
        json = new String(bytes1);
        return json;
    }

    //doGet
    public void doGet(final String httpurl, final MyCallBase myCallBase){
        new Thread(new Runnable() {
            @Override
            public void run() {
                InputStream inputStream = null;
                try {
                    URL url = new URL(httpurl);
                    HttpURLConnection httpurlConnection = (HttpURLConnection) url.openConnection();
                    httpurlConnection.setRequestMethod("GET");
                    httpurlConnection.setConnectTimeout(5000);
                    httpurlConnection.setReadTimeout(5000);
                    //启动
                    httpurlConnection.connect();
                    inputStream = httpurlConnection.getInputStream();
                    final String json = inToString(inputStream);
                    if (inputStream==null){
                        //成功
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Log.i("TAG", "成功"+json);
                                myCallBase.doGetsu(json);
                            }
                        });
                    }else{
                        //失败
                        Log.i("TAG", "失败");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    //失败
                    Log.i("TAG", "失败");
                }finally {
                    //关闭流
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
    //接口
    public interface MyCallBase{
        //成功
        void doGetsu(String json);
        //失败
        void doGetEr();
    }
}
