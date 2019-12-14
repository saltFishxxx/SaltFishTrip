package cn.itcast.travel.test;

import cn.itcast.travel.dao.impl.RouteImgDao;
import cn.itcast.travel.dao.impl.RouteImgDaoImpl;
import cn.itcast.travel.domain.RouteImg;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RouteImgTest {
    private RouteImgDao routeImgDao = new RouteImgDaoImpl();

    @Test
    public void testFindImgs() {
        List<RouteImg> imgs = routeImgDao.findImgs(3);
        for (RouteImg routeImg : imgs) {
            System.out.println(routeImg.getBigPic());
            System.out.println(routeImg.getSmallPic());
        }

    }
}
