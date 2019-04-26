package com.example.admin.woailiushuang.http;

import java.io.InputStream;

public interface CallbackListener {

    void onSuccess(InputStream inputStream);


    void onFailure();
}
