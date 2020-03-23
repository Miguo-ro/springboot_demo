package com.miguo.blog.service;

import com.miguo.blog.po.User;

public interface UserService {
    User checkUser(String username,String password);
}
