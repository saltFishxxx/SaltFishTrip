package cn.itcast.travel.dao.impl;

import cn.itcast.travel.domain.Favorite;

public interface FavoriteDao {
    public int favoriteCount(int rid);
    public Favorite isFlag(int rid, int uid);
    public int addFavorite(int rid, int uid);
}
