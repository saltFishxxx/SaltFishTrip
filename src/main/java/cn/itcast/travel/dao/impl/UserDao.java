package cn.itcast.travel.dao.impl;

import cn.itcast.travel.domain.User;

public interface UserDao {
    public User findUserByName(String username);
    public boolean insertUser(User user);
    public User checkActive(String code);
    public boolean updateUserStatus(String code);
    public User findUserByNameAndPassword(User user);
}
