package cn.itcast.travel.dao.impl;

import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements UserDao{

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public User findUserByName(String username) {
        User user = null;
        String sql = "select * from tab_user where username = ?";
        try {
            user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
        }catch (Exception e) {

        }
        return user;
}

    @Override
    public boolean insertUser(User user) {
        boolean flag = false;
        String sql = "insert into tab_user values (null,?,?,?,?,?,?,?,?,?)";
        int update = jdbcTemplate.update(sql, user.getUsername(), user.getPassword(),
                user.getName(), user.getBirthday(), user.getSex(), user.getTelephone(),
                user.getEmail(), user.getStatus(), user.getCode());
        if (update>0) {
            flag = true;
        }
        return flag;
    }

    @Override
    public User checkActive(String code) {
        String sql = "select * from tab_user where code = ?";
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), code);
        }catch (Exception e) {

        }
        return user;
    }

    @Override
    public boolean updateUserStatus(String code) {
        String sql = "update tab_user set status = 'Y' where code = ?";
        int update = jdbcTemplate.update(sql, code);
        if (update <= 0) {
            return false;
        }
        return true;
    }

    @Override
    public User findUserByNameAndPassword(User user) {
        String sql = "select * from tab_user where username = ? and password = ?";
        User sqlUser = null;
        try {
            sqlUser = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), user.getUsername(), user.getPassword());
        }catch (Exception e) {

        }
        return sqlUser;
    }
}
