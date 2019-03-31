package com.example.admin.woailiushuang.consts;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @desc
 * @auth ${user}
 * @time 2019/3/26 12:02
 */
public class EveBus {

    private Map<Object,List<SubscripeMethod>> cachMap;
    private static volatile EveBus instance;

    private EveBus(){

        cachMap = new HashMap<>();
    }



    public static EveBus getDefult(){
                if (instance==null){
                    synchronized ((EveBus.class)){
                        if (instance==null){
                            instance = new EveBus();
                        }
                    }
                }

    return instance;
    }

    public void register(Object obj){

    }

}
