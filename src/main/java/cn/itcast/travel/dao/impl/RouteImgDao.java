package cn.itcast.travel.dao.impl;


import cn.itcast.travel.domain.RouteImg;

import java.util.List;

public interface RouteImgDao {
    public List<RouteImg> findImgs(int rid);
}
