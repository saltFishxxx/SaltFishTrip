package cn.itcast.travel.service.impl;


import cn.itcast.travel.dao.impl.CategoryDao;
import cn.itcast.travel.dao.impl.CategoryDaoimpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CategoryServiceImpl implements CategorySerevice{
   private CategoryDao categoryDao = new CategoryDaoimpl();

   public List<Category> findAll() {
       Jedis jedis = JedisUtil.getJedis();
       List<Category> all = new ArrayList<>();
       Set<Tuple> jCategory = jedis.zrangeWithScores("category", 0, -1);
//       Set<String> jCategory = jedis.zrange("category", 0, -1);
       if (jCategory != null && jCategory.size()>0) {
           for (Tuple tuple : jCategory) {
               Category category = new Category();
               category.setCname(tuple.getElement());
               category.setCid((int)tuple.getScore());
               all.add(category);
           }

           System.out.println("查询redis");
       }else {
           all = categoryDao.getAll();
           for (Category category : all) {
               jedis.zadd("category", category.getCid(), category.getCname());
           }
           System.out.println("查询数据库");
       }
       return all;
   }
}
