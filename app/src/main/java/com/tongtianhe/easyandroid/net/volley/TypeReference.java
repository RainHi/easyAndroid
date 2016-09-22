package com.tongtianhe.easyandroid.net.volley;


import java.lang.reflect.Type;

/**
 * Created by free on 16/8/29.
 * 用以获取泛型参数的封装类
 * ex，比如要获取<List<String>>这样的泛型参数，可使用如下代码
 * new TypeReference<List<String>>(){}.getType();
 * 在gson中使用
 * List<String> strings=new Gson().fromJson(stringJson, new TypeReference<List<String>>(){}.getType());
 */
public class TypeReference<T> {
    private final Type type;

    protected TypeReference() {
        Type superClass = getClass().getGenericSuperclass();
        this.type = ((java.lang.reflect.ParameterizedType) superClass).getActualTypeArguments()[0];
    }

    public Type getType() {
        return this.type;
    }

}
