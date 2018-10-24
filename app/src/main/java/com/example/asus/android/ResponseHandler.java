package com.example.asus.android;

public interface ResponseHandler
{
    void success(CommonResponse response);

    void fail(String failCode, String failMsg);
}
