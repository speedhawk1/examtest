package com.example.service;

import com.example.util.Pagination;

import java.io.Serializable;
import java.util.List;

/**
 *
 * Created by Administrator on 2015/12/27.
 */
public interface GenericService<T extends Serializable, ID extends  Serializable> {
    void add(T t);
    void remove(ID id);
    void modify(T model);
    Pagination<T> list(int currentPage);
    Pagination<T> query(int currentPage, String statement, Object parameter);
    List<T> queryAll(String statement, Object parameter);
    T query(T t);
    T search(ID id);
}
