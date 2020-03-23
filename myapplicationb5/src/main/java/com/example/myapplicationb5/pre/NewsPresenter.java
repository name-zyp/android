package com.example.myapplicationb5.pre;

import com.example.myapplicationb5.base.BasePresenter;
import com.example.myapplicationb5.base.DataCall;
import com.example.myapplicationb5.bean.Result;
import com.example.myapplicationb5.model.NewsModel;

public class NewsPresenter extends BasePresenter {

    public NewsPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Result getModel(Object... args) {
        return NewsModel.getNews((int) args[0]);
    }
}
