package com.example.dao.impl;

import com.example.dao.GenericDao;
import com.example.util.Constant;
import com.example.util.Pagination;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 *
 * Created by Administrator on 2015/12/27.
 */
public class GenericDaoImpl<T extends Serializable, ID extends Serializable> implements GenericDao<T, ID> {

    private String namespace;

    @Autowired
    private SqlSession sqlSession;

    @SuppressWarnings("unchecked")
    public GenericDaoImpl() {
        ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
        Class<T> modelClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
        namespace = modelClass.getSimpleName().toLowerCase().concat(".");
    }

    @Override
    public void add(T t) {
        sqlSession.insert(namespace.concat("add"), t);
    }

    @Override
    public void remove(ID id) {
        sqlSession.delete(namespace.concat("remove"), id);
    }

    @Override
    public T query(T model) {
        model = sqlSession.selectOne(namespace.concat("query"), model);
        return model;
    }

    @Override
    public T search(ID id) {
        return sqlSession.selectOne(namespace.concat("search"), id);
    }

    @Override
    public Pagination<T> list(int page) {
        return page(page, null, "list");
    }

    @Override
    public Pagination<T> query(int page, String statement, Object parameter) {
        return page(page, parameter, statement);
    }

    @Override
    public void modify(T model) {
        sqlSession.update(namespace.concat("modify"), model);
    }

    // Pagination

    private Pagination<T> page(int page, Object parameter, String statement) {
        List<T> list = sqlSession.selectList(namespace.concat(statement), parameter, getRowBounds(page));
        return getPagination(page, list, statement, parameter);
    }

    private RowBounds getRowBounds(int page) {
        int offset = Constant.PAGE_SIZE * (page - 1);
        return new RowBounds(offset, Constant.PAGE_SIZE);
    }

    private Pagination<T> getPagination(int page, List<T> list, String statement, Object parameter) {
        int totalRows = getTotalRows(statement, parameter);
        int totalPages = (int) Math.ceil(totalRows / (double) Constant.PAGE_SIZE);
        return new Pagination<>(list, statement, Constant.PAGE_SIZE, totalRows, totalPages, page);
    }

    private int getTotalRows(String selectId, Object parameter) {
        List<T> list = sqlSession.selectList(namespace.concat(selectId), parameter);
        return list.size();
    }
}
