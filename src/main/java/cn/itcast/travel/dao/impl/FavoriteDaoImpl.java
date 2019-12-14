package cn.itcast.travel.dao.impl;

import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;

public class FavoriteDaoImpl implements FavoriteDao{
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public int favoriteCount(int rid) {
        String sql  = "Select count(*) from tab_favorite where rid = ?";
        int count = 0;
        try {
            count = jdbcTemplate.queryForObject(sql, Integer.class, rid);
        }catch (Exception e) {

        }
        return count;
    }

    @Override
    public Favorite isFlag(int rid, int uid) {
        String sql = "select * from tab_favorite where rid = ? and uid = ?";
        Favorite favorite = null;
        try {
            favorite = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Favorite>(Favorite.class), rid, uid);
        }catch (Exception e) {

        }
        return favorite;
    }

    public int addFavorite(int rid, int uid) {
        String sql = "insert into tab_favorite values(?,?,?)";
        return jdbcTemplate.update(sql, rid, new Date(), uid);
    }
}
