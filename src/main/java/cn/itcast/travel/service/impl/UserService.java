package cn.itcast.travel.service.impl;

import cn.itcast.travel.domain.User;

public interface UserService {
    public boolean insertUser(User user);
    public User findUserByName(String name);
    public boolean activeUser(String code);
    public User login(User user);
}
