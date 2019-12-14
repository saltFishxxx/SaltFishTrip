//package cn.itcast.travel.web.filter;
//
//import cn.itcast.travel.domain.User;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebFilter("/*")
//public class UserFilter implements Filter {
//    public void destroy() {
//    }
//
//    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
//        HttpServletRequest request = (HttpServletRequest) req;
//        HttpServletResponse response = (HttpServletResponse) resp;
//        String requestURI = request.getRequestURI();
//        if (requestURI.contains("login.html") || requestURI.contains("css") || requestURI.contains("fonts")
//        || requestURI.contains("images") || requestURI.contains("image") || requestURI.contains("js") ||
//        requestURI.contains("checkcode")){
//            chain.doFilter(req, resp);
//        }else {
//            User user = (User) request.getSession().getAttribute("user");
//            if(user == null) {
//                response.sendRedirect("/travel/login.html?error=1");
//            }else {
//                chain.doFilter(req, resp);
//            }
//        }
//    }
//
//    public void init(FilterConfig config) throws ServletException {
//
//    }
//
//}
