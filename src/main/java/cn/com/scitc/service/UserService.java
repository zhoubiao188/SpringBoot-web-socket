package cn.com.scitc.service;

import cn.com.scitc.entity.User;

public interface UserService {

    User addUser(String username,String password,String nickname);

    User findByUsernameUser(String username);
}
