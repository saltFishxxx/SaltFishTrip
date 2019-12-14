package cn.itcast.travel.dao.impl;

import cn.itcast.travel.domain.Route;

import java.util.List;

public interface RouteDao {
    public int totalCount(String condition);
    public int totalCountByCid(Integer cid, String condition);
    public List<Route> getAllByCidPage(int cid, int start, int end, String condition);
    public List<Route> getAllPage(int start, int pageNumber, String condition);
    public Route findOne(int rid);
}
