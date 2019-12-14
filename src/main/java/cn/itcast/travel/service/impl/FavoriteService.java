package cn.itcast.travel.service.impl;

public interface FavoriteService {
    public boolean isFlag(String rid, int uid);
    public boolean addFavoret(String rid, int uid);
    public int countFavorite(String rid);
}
