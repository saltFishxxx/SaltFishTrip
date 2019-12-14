package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.impl.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;
import cn.itcast.travel.util.JedisUtil;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.UuidUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();

    //激活
    public void active(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String code = request.getParameter("code");
        boolean flag = userService.activeUser(code);
        ResultInfo resultInfo = new ResultInfo();
        if (flag) {
            response.getWriter().write("<h2><span>激活成功，<span><a href='http://localhost/travel/login.html'>请登录<a></h2>");
        }else {
            response.getWriter().write("<h2>激活失败，请联系管理员<h2>");
        }
    }

    //登陆
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
        User user = new User();
        try {
            BeanUtils.populate(user, request.getParameterMap());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        ResultInfo resultInfo = new ResultInfo();
        String check = request.getParameter("check");
        String checkcode_server = (String) request.getSession().getAttribute("CHECKCODE_SERVER");
        request.getSession().removeAttribute("CHECKCODE_SERVER");
        if (check == null || !check.equalsIgnoreCase(checkcode_server)) {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("验证码错误");
            writeValue(resultInfo, response);
            return;
        }

        User sqlUser = userService.login(user);
        System.out.println(sqlUser);
        if (sqlUser == null) {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("用户名或密码错误");
        }else if(sqlUser.getStatus()==null || sqlUser.getStatus().equals("N")) {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("未激活账号");
        }else {
            request.getSession().setAttribute("user", sqlUser);
            Jedis jedis = JedisUtil.getJedis();
            jedis.set("user", "1");
            resultInfo.setFlag(true);
        }
        writeValue(resultInfo, response);
    }

    //注册
    public void register(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
        User user = new User();
        try {
            BeanUtils.populate(user, request.getParameterMap());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        ResultInfo resultInfo = new ResultInfo();

        String checkcode_server = (String) request.getSession().getAttribute("CHECKCODE_SERVER");
        request.getSession().removeAttribute("CHECKCODE_SERVER");
        String check = request.getParameter("check");
        response.setContentType("application/json;charset=utf-8");
        ObjectMapper objectMapper = new ObjectMapper();
        if (check != null && check.length()>0) {
            if (!check.equalsIgnoreCase(checkcode_server)) {
                resultInfo.setErrorMsg("验证码错误");
                resultInfo.setFlag(false);
                response.getWriter().write(objectMapper.writeValueAsString(resultInfo));
                return;
            }
        }
        user.setStatus("N");
        user.setCode(UuidUtil.getUuid());
        if (userService.insertUser(user)) {
            resultInfo.setFlag(true);
            resultInfo.setErrorMsg("注册成功");
            MailUtils.sendMail(user.getEmail(), "<a href='http://localhost/travel/user/active?code="+user.getCode()+"'>激活账号</a>", "激活邮件");
        }else {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("注册失败，请联系客服");
        }
        response.getWriter().write(objectMapper.writeValueAsString(resultInfo));
    }

    //用户名校验
    public void findUserByName(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        User userByName =
                userService.findUserByName(username);
        ResultInfo resultInfo = new ResultInfo();
        if (userByName!=null) {
            resultInfo.setErrorMsg("已存在此用户");
            resultInfo.setFlag(false);
        }else {
            resultInfo.setFlag(true);
            resultInfo.setErrorMsg("可以使用");
        }
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(new ObjectMapper().writeValueAsString(resultInfo));
    }

    public void loginStatus(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = (User) request.getSession().getAttribute("user");
        if (user!=null) {
            writeValue(user.getUsername(), response);
        }

    }

    public void logOut(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath()+"/login.html");
    }
}
