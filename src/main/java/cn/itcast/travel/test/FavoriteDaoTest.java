package cn.itcast.travel.test;

import cn.itcast.travel.dao.impl.FavoriteDao;
import cn.itcast.travel.dao.impl.FavoriteDaoImpl;
import cn.itcast.travel.domain.Favorite;
import org.junit.jupiter.api.Test;

public class FavoriteDaoTest {
    private FavoriteDao favoriteDao = new FavoriteDaoImpl();

    @Test
    public void testAddFavorite() {
        int i = favoriteDao.addFavorite(3, 7);
        System.out.println(i);
    }

    @Test
    public void testIsFlag() {
        Favorite flag = favoriteDao.isFlag(3, 1);
        System.out.println(flag);
    }

    @Test
    public void testFavoriteCount() {
        int i = favoriteDao.favoriteCount(3);
        System.out.println(i);
    }
}
