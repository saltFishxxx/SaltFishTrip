package cn.itcast.travel.test;

import cn.itcast.travel.dao.impl.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Properties;
import java.util.UUID;

public class UserDaoTest {
    private UserDao userDao = new UserDaoImpl();

    @Test
    public void tesFindUserByName() {
        User username = userDao.findUserByName("小明");
        System.out.println(username);
        System.out.println(username.getPassword());
    }

    @Test
    public void testInsertUser() {
        User user = new User();
        user.setBirthday("1998-3-22");
        user.setCode(UUID.randomUUID().toString().replace("-",""));
        user.setEmail("34334@qq.com");
        user.setName("小红");
        user.setPassword("22222");
        user.setTelephone("32423434");
        user.setSex("男");
        user.setStatus("N");
        user.setUsername("小雨后1");
        boolean b = userDao.insertUser(user);
        System.out.println(b);
    }

    @Test
    public void testCheckActive() {
        User user = userDao.checkActive("72e872af692e43f8b63200f0d5414e30");
        System.out.println(user);
        System.out.println(user.getStatus());
    }

    @Test
    public void testUpdateUserStatus() {
        boolean b = userDao.updateUserStatus("271439c1733b4bdfa3e101e2adf8b57e");
        System.out.println(b);
    }

    @Test
    public void testFindUserByNameAndPassword() {
        User user = new User();
        user.setUsername("2016211345345x");
        user.setPassword("222222333");
        User userByNameAndPassword = userDao.findUserByNameAndPassword(user);
        System.out.println(userByNameAndPassword.getStatus());
    }


}
