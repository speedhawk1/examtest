package com.example.service;

import com.example.util.Pagination;

import java.io.Serializable;

/**
 *
 * Created by Administrator on 2015/12/27.
 */
public interface GenericService<T extends Serializable, ID extends  Serializable> {
    void add(T t);
    void remove(ID id);
    void modify(T model);
    Pagination<T> list(int page);
    T query(T t);
    T search(ID id);
}
