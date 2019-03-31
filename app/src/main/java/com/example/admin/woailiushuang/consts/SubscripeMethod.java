package com.example.admin.woailiushuang.consts;

import java.lang.reflect.Method;

/**
 * @desc
 * @auth ${user}
 * @time 2019/3/26 16:06
 */
public class SubscripeMethod {
    //fangfa
    private Method mMethod;

    //线程类型模式
    private ThMode mThMode;

    //leixing
    private Class<?> type;

    public SubscripeMethod(Method mMethod, ThMode mThMode, Class<?> type) {
        this.mMethod = mMethod;
        this.mThMode = mThMode;
        this.type = type;
    }

    public Method getmMethod() {

        return mMethod;
    }

    public void setmMethod(Method mMethod) {
        this.mMethod = mMethod;
    }

    public ThMode getmThMode() {
        return mThMode;
    }

    public void setmThMode(ThMode mThMode) {
        this.mThMode = mThMode;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }
}
