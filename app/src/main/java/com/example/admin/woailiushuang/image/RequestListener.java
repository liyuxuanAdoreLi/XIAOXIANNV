package com.example.admin.woailiushuang.image;

import android.graphics.Bitmap;

public interface RequestListener {
    void onSuccess(Bitmap bitmap);
    void onError();
}
