package com.example.myapplicationb5.base;

import com.example.myapplicationb5.bean.Result;

public interface DataCall<T> {
    //成功
    void success(Result<T> result);
    //失败
    void fail();
}
