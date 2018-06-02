package com.sinobest.framework.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by Administrator on 2017/7/23.
 *
 */
public class EntltyUtil<T> {
    private Class<T>  entityClass;

    public EntltyUtil(){
        this.entityClass = null;
        Class<?> c = getClass();
        Type type = c.getGenericSuperclass();
        if(type instanceof ParameterizedType){
            Type[] paramterizedType = ((ParameterizedType)type).getActualTypeArguments();
            this.entityClass = (Class<T>) paramterizedType[0];
        }
    }
}
