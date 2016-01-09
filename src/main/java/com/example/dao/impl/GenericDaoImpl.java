package com.example.dao.impl;

import com.example.dao.GenericDao;
import com.example.util.Constant;
import com.example.util.Pagination;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by Administrator on 2015/12/27.
 */
public class GenericDaoImpl<T extends Serializable, ID extends Serializable> implements GenericDao<T, ID> {

    private String namespace;

    @Autowired
    protected SqlSessionFactory sqlSessionFactory;

    @SuppressWarnings("unchecked")
    public GenericDaoImpl() {
        ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
        Class<T> modelClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
        namespace = modelClass.getSimpleName().toLowerCase().concat(".");
    }

    @Override
    public void add(T t) {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        sqlSession.insert(namespace.concat("add"), t);
        sqlSession.close();
    }

    @Override
    public void remove(ID id) {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        sqlSession.delete(namespace.concat("remove"), id);
        sqlSession.close();
    }

    @Override
    public T query(T model) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        model = sqlSession.selectOne(namespace.concat("query"), model);
        sqlSession.close();
        return model;
    }

    @Override
    public T search(ID id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        T model = sqlSession.selectOne(namespace.concat("search"), id);
        sqlSession.close();
        return model;
    }

    @Override
    public Pagination<T> list(int page) {
        return page(page, null, "list");
    }

    @Override
    public void modify(T model) {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        sqlSession.update(namespace.concat("modify"), model);
        sqlSession.close();
    }

    // Pagination

    private Pagination<T> page(int page, Object parameter, String selectId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<T> list = sqlSession.selectList(namespace.concat(selectId), parameter, getRowBounds(page));
        sqlSession.close();
        return getPagination(page, list, selectId, parameter);
    }

    private RowBounds getRowBounds(int page) {
        int offset = Constant.PAGE_SIZE * (page - 1);
        return new RowBounds(offset, Constant.PAGE_SIZE);
    }

    private Pagination<T> getPagination(int page, List<T> list, String selectId, Object parameter) {
        int totalRows = getTotalRows(selectId, parameter);
        int totalPages = (int) Math.ceil(totalRows / (double) Constant.PAGE_SIZE);
        return new Pagination<>(list, selectId, Constant.PAGE_SIZE, totalRows, totalPages, page);
    }

    private int getTotalRows(String selectId, Object parameter) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<T> list = sqlSession.selectList(namespace.concat(selectId), parameter);
        sqlSession.close();
        return list.size();
    }
}
