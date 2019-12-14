package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.impl.FavoriteDao;
import cn.itcast.travel.dao.impl.FavoriteDaoImpl;
import cn.itcast.travel.domain.Favorite;

public class FavoriteServiceImpl implements FavoriteService {
    private FavoriteDao favoriteDao = new FavoriteDaoImpl();

    @Override
    public boolean isFlag(String rid, int uid) {
        Favorite flag = favoriteDao.isFlag(Integer.parseInt(rid), uid);
        if (flag == null) {
            return false;
        }
        return true;
    }

    @Override
    public boolean addFavoret(String rid, int uid) {
        if (isFlag(rid, uid)) {
            return false;
        }
        boolean flag = false;
        int i = favoriteDao.addFavorite(Integer.parseInt(rid), uid);
        if (i>0) {
            flag = true;
        }
        return flag;
    }

    @Override
    public int countFavorite(String rid) {
        return favoriteDao.favoriteCount(Integer.parseInt(rid));
    }
}
