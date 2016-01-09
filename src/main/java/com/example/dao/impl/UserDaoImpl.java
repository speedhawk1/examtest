package com.example.dao.impl;

import com.example.dao.UserDao;
import com.example.model.User;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2015/12/27.
 */
@Repository
public class UserDaoImpl extends GenericDaoImpl<User, Integer> implements UserDao {
}