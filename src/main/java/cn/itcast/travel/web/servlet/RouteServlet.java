package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.impl.RouteService;
import cn.itcast.travel.service.impl.RouteServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {
    private RouteService routeService = new RouteServiceImpl();

    public void findAllById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String cid = request.getParameter("cid");
        System.out.println("cid:"+cid);
        String condition = request.getParameter("condition");
        if ("undefined".equals(condition)) {
            condition = null;
        }
        if (condition != null && !"".equals(condition) ) {
            condition = new String(condition.getBytes("iso-8859-1"), "utf-8");
        }
        String currentPage = request.getParameter("currentPage");
        int pageNumber = 5;
        PageBean<Route> routePageBean = new PageBean<>();
        routePageBean.setPageNumber(pageNumber);

        //参数处理
        if (cid == null || "".equals(cid) || "undefined".equals(cid)) {
            cid = "5";
        }
        if (currentPage == null || "".equals(currentPage) ) {
            routePageBean.setCurrentPage(1);
        }else {
            routePageBean.setCurrentPage(Integer.parseInt(currentPage));
        }

        //获得分页数据
        PageBean<Route> allByCid = routeService.findAllByCid(Integer.parseInt(cid), routePageBean, condition);
        //发送数据
        writeValue(allByCid, response);
    }

    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rid = request.getParameter("rid");
        if (rid == null || "".equals(rid)) {
            return;
        }
        Route one = routeService.findOne(Integer.parseInt(rid));
        writeValue(one, response);
    }
}
