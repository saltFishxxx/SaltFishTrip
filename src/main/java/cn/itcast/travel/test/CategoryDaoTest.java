package cn.itcast.travel.test;

import cn.itcast.travel.dao.impl.CategoryDao;
import cn.itcast.travel.dao.impl.CategoryDaoimpl;
import cn.itcast.travel.domain.Category;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CategoryDaoTest {
    private CategoryDao categoryDao = new CategoryDaoimpl();

    @Test
    public void testFindAll() {
        List<Category> all = categoryDao.getAll();
        for (Category category : all) {
            System.out.println(category.getCid());
            System.out.println(category.getCname());
        }

    }
}
