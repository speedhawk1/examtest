package com.example.service;

import com.example.model.User;

/**
 *
 * Created by Administrator on 2015/12/27.
 */
public interface UserService extends GenericService<User, Integer> {
    User login(User user);

    boolean register(User user);
}
