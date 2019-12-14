package cn.itcast.travel.dao.impl;

import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl implements RouteDao{
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public int totalCount(String condition) {
        String sql = "select count(*) from tab_route";
        int count = 0;
        try {
            count = jdbcTemplate.queryForObject(sql, Integer.class);
        }catch (Exception e) {

        }
        return count;
    }

    @Override
    public int totalCountByCid(Integer cid, String condition) {
        String sql = "select count(*) from tab_route where cid = ?";
        StringBuilder stringBuilder = new StringBuilder(sql);
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(cid);
        if (condition != null && !"".equals(condition)) {
            stringBuilder.append(" and rname like ? ");
            objects.add("%"+condition+"%");
        }
        int count = 0;
        System.out.println(stringBuilder.toString());
        try {
            count = jdbcTemplate.queryForObject(stringBuilder.toString(), Integer.class, objects.toArray());
        }catch (Exception e) {

        }
        return count;
    }


    @Override
    public List<Route> getAllByCidPage(int cid, int start, int pageNumber, String condition) {
        String sql = "select * from tab_route where cid = ? ";
        StringBuilder stringBuilder = new StringBuilder(sql);
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(cid);
        if (condition!=null && !"".equals(condition)) {
            stringBuilder.append(" and rname like ? ");
            objects.add("%"+condition+"%");
        }
        objects.add(start);
        objects.add(pageNumber);
        stringBuilder.append(" limit ?,? ");
        System.out.println(stringBuilder.toString());
        System.out.println(objects);
        List<Route> query = jdbcTemplate.query(stringBuilder.toString(), new BeanPropertyRowMapper<Route>(Route.class), objects.toArray());
        System.out.println(query);
        System.out.println(111);
        return query;
    }


    @Override
    public List<Route> getAllPage(int start, int end, String condition) {
        String sql = "select * from tab_route where cid = ? limit ?,?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Route>(Route.class), start,end);
    }

    @Override
    public Route findOne(int rid) {
        String sql = "select * from tab_route where rid = ?";
        Route route = null;
        try {
           route = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Route>(Route.class), rid);
        }catch (Exception e) {

        }
        return route;

    }
}
