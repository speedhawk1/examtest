package com.bdc.dao.impl;

import com.bdc.dao.UserDao;
import com.bdc.model.User;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2015/12/27.
 */
@Repository
public class UserDaoImpl extends GenericDaoImpl<User, Integer> implements UserDao {
}