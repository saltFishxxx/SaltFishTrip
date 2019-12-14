package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.impl.*;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.domain.Seller;

import java.util.List;


public class RouteServiceImpl implements RouteService{
    private RouteDao routeDao = new RouteDaoImpl();
    private SellerDao sellerDao = new SellerDaoImpl();
    private RouteImgDao routeImgDao = new RouteImgDaoImpl();
    private FavoriteDao favoriteDao = new FavoriteDaoImpl();

    @Override
    public PageBean<Route> findAllByCid(Integer cid, PageBean<Route> pageBean, String condition ) {
        PageBean<Route> routePageBean = new PageBean<>();

        int totalCount = routeDao.totalCountByCid(cid, condition);
        int pageCount = totalCount%pageBean.getPageNumber() == 0 ? totalCount/pageBean.getPageNumber() : totalCount/pageBean.getPageNumber() + 1;
        //分页数据
        routePageBean.setList(routeDao.getAllByCidPage(cid, (pageBean.getCurrentPage()-1)*pageBean.getPageNumber(), pageBean.getPageNumber(), condition));
        //当前页数
        routePageBean.setCurrentPage(pageBean.getCurrentPage());
        //每页显示数据数
        routePageBean.setPageNumber(pageBean.getPageNumber());
        //总数据数
        routePageBean.setTotalCount(totalCount);
        //总页数
        routePageBean.setTotalPage(pageCount);
        return routePageBean;
    }

    @Override
    public Route findOne(int rid) {
        Route one = routeDao.findOne(rid);
        List<RouteImg> imgs = routeImgDao.findImgs(rid);
        one.setRouteImgList(imgs);
        Seller seller = sellerDao.findOne(one.getSid());
        one.setSeller(seller);
        one.setCount(favoriteDao.favoriteCount(rid));
        return one;
    }
}
