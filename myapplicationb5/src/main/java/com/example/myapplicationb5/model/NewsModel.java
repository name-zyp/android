package com.example.myapplicationb5.model;

import com.example.myapplicationb5.bean.Newsinfo;
import com.example.myapplicationb5.bean.Result;
import com.example.myapplicationb5.util.NetUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class NewsModel {
   public static Result<Newsinfo> getNews(int page){
        String string = NetUtil.getInstance()
                .doGet("http://47.94.132.125/baweiapi/gank_android?page="+page+"&pageSize=5");
       Gson gson = new Gson();
       Type type = new TypeToken<Result<Newsinfo>>(){}.getType();
       Result<Newsinfo> data = gson.fromJson(string,type);
       return data;
   }
}
