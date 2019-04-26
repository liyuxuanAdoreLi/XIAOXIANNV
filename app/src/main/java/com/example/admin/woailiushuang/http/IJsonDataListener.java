package com.example.admin.woailiushuang.http;

public interface IJsonDataListener <T> {
    void onSuccess(T m);
    void onFailure();
}
