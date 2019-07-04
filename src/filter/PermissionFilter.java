package filter;

import bean.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/Query","/Delete","/Alter","/Add","/manage_page/*"})
public class PermissionFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpSession session = ((HttpServletRequest) req).getSession();
        User user = (User) session.getAttribute("userinfo");
        if (user == null) {//session中没有用户信息，说明没有登录
            req.setAttribute("nametip", "请您先登录");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        } else if (!"管理员".equals(user.getUsertype())) {//用户身份不是管理员，跳转到自己的个人信息页面
            req.getRequestDispatcher("PersonDetailServlet").forward(req,resp);
        } else if ("管理员".equals(user.getUsertype())) {//是管理员，不拦截请求
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
