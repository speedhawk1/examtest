package com.bdc.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2015/12/27.
 */
public interface GenericDao<T extends Serializable, ID extends Serializable> {
    void add(T t);
    void remove(ID id);
    void modify(T model);
    List<T> list();
    T query(T t);
    T search(ID id);
}
