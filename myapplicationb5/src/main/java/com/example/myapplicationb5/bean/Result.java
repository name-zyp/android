package com.example.myapplicationb5.bean;

import java.util.List;

public class Result<T> {
    public int code;
    public String msg;
    public List<T> result;
}
