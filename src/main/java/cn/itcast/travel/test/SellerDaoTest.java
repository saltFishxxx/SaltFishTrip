package cn.itcast.travel.test;

import cn.itcast.travel.dao.impl.SellerDao;
import cn.itcast.travel.dao.impl.SellerDaoImpl;
import cn.itcast.travel.domain.Seller;
import org.junit.jupiter.api.Test;

public class SellerDaoTest {
    private SellerDao sellerDao = new SellerDaoImpl();

    @Test
    public void testFindOne() {
        Seller one = sellerDao.findOne(1);
        System.out.println(one.getAddress());
        System.out.println(one.getConsphone());
    }
}
