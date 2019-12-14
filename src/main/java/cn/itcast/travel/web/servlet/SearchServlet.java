package cn.itcast.travel.web.servlet;

import cn.itcast.travel.service.impl.RouteService;
import cn.itcast.travel.service.impl.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/SearchServlet")
public class SearchServlet extends BaseServlet {
    private RouteService routeService = new RouteServiceImpl();
    public void getByCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> parameterMap = request.getParameterMap();

    }
}
