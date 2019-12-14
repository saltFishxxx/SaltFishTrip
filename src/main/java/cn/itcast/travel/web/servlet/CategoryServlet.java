package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.impl.CategorySerevice;
import cn.itcast.travel.service.impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.io.IOException;
import java.util.List;

@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet {
    private CategorySerevice categorySerevice = new CategoryServiceImpl();

    public void getAll(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
        List<Category> all = categorySerevice.findAll();
        writeValue(all, response);
    }
}
