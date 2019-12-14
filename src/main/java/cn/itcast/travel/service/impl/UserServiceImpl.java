package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.impl.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();

    @Override
    public boolean insertUser(User user) {
        return userDao.insertUser(user);
    }

    @Override
    public User findUserByName(String name) {
        return userDao.findUserByName(name);
    }

    @Override
    public boolean activeUser(String code) {
        User user = userDao.checkActive(code);
        if (user != null && user.getStatus().equals("N")) {
            return userDao.updateUserStatus(code);
        }else {
            return false;
        }
    }

    @Override
    public User login(User user) {
       return userDao.findUserByNameAndPassword(user);
    }
}
