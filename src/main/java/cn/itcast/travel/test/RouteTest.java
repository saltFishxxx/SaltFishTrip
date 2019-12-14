package cn.itcast.travel.test;

import cn.itcast.travel.dao.impl.RouteDao;
import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.dao.impl.UserDao;
import cn.itcast.travel.domain.Route;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RouteTest {
    private RouteDao routeDao = new RouteDaoImpl();

    @Test
    public void testTotalCount() {
        int i = routeDao.totalCount("宁夏");
        System.out.println(i);
    }

    @Test
    public void testTotalCountByCid() {
//        System.out.println(routeDao.toatlCountByCid(3));
    }

    @Test
    public void testFindAllByCidPage() {
        List<Route> routes = routeDao.getAllByCidPage(5, 0, 5, "北京");
        System.out.println(routes);
        for (Route route : routes) {
            System.out.println(route.getIsThemeTour());
            System.out.println(route.getRimage());
        }
    }

    @Test
    public void testTotalCountByCidPage() {
        int count = routeDao.totalCountByCid(5, "宁夏");
        System.out.println(count);
    }

    @Test
    public void testFindOne() {
        Route one = routeDao.findOne(3);
        System.out.println(one.getRimage());
        System.out.println(one.getCategory());
    }
}
