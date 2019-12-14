package cn.itcast.travel.service.impl;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;

public interface RouteService {
    public PageBean<Route> findAllByCid(Integer cid, PageBean<Route> routePageBean, String condition);
    public Route findOne(int rid);
}
