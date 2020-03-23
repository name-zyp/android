package com.example.myapplicationb5.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetUtil {
    //单例
    public NetUtil(){
    }
    public static NetUtil getInstance(){
        return NETUTIL;
    }
    public static final NetUtil NETUTIL = new NetUtil();
    //流转字符串
    public String inToString (InputStream inputStream) throws Exception {
        //读写三件套
        byte[] bytes = new byte[1024];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int len = -1;
        while ((len = inputStream.read())!=-1){
            byteArrayOutputStream.write(bytes,0,len);
        }
        byte[] bytes1 = byteArrayOutputStream.toByteArray();
        String json = new String(bytes1);
        return json;
    }
    //doget
    public String doGet(String httpurl){
        InputStream inputStream = null;
        try {
            URL url = new URL(httpurl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode()==200){
                //网络成功
                inputStream = httpURLConnection.getInputStream();
                String json = inToString(inputStream);
                //返回json串
                return json;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
