package com.example.service.impl;

import com.example.dao.GenericDao;
import com.example.model.User;
import com.example.service.UserService;
import com.example.util.Constant;
import org.apache.commons.codec.digest.DigestUtils;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * Created by Administrator on 2015/12/27.
 */
@Service
public class UserServiceImpl extends GenericServiceImpl<User, Integer> implements UserService {

    private StrongPasswordEncryptor encryptor;

    @Override
    @Autowired
    @Qualifier("userDaoImpl")
    public void setGenericDao(GenericDao<User, Integer> genericDao) {
        super.genericDao = genericDao;
        encryptor = new StrongPasswordEncryptor();
    }

    @Override
    public User login(User user) {
        String plainPassword = user.getPassword();
        user.setPassword(null);
        user = genericDao.query(user);
        if (user != null) {
            String encryptedPassword = user.getPassword();
            if (encryptor.checkPassword(plainPassword, encryptedPassword)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean register(User user) {
        String password = user.getPassword();
        user.setPassword(null);
        if (genericDao.query(user) != null) {
            return false;
        }
        user.setPassword(encryptor.encryptPassword(password));
        genericDao.add(user);
        return true;
    }
}

// salt
