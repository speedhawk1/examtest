package com.example.service.impl;

import com.example.dao.GenericDao;
import com.example.model.User;
import com.example.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2015/12/27.
 */
@Service
public class UserServiceImpl extends GenericServiceImpl<User, Integer> implements UserService {

    @Override
    @Autowired
    @Qualifier("userDaoImpl")
    public void setGenericDao(GenericDao<User, Integer> genericDao) {
        super.genericDao = genericDao;
    }

    @Override
    public User login(User user) {
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        user = genericDao.query(user);
        if (user != null) {
            return user;
        } else {
            return null;
        }
    }

    @Override
    public boolean register(User user) {
        String password = user.getPassword();
        user.setPassword(null);
        if (genericDao.query(user) != null) {
            return false;
        }
        user.setPassword(DigestUtils.md5Hex(password));
        genericDao.add(user);
        return true;
    }
}
