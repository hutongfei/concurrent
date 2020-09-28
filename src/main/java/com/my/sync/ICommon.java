package com.my.sync;

import java.util.List;

public interface ICommon<T> {

    int code = 200;

    String msg = "success";

    void add(T t);

    T get(String id);

    List<T> getList(T t);


}
