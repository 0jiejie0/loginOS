package servlet;

import bean.User;
import dao.UserDao;
import daoImpl.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Login",value = "/Login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        User user=new User();
        user.setUsername(username);
        user.setPassword(password);
        UserDao userDao= DaoFactory.getUserDaoImpl();
        if (userDao.login(user)){//登录成功
            session.setAttribute("userinfo",userDao.getInfo());
//            request.getRequestDispatcher("Query").forward(request,response);//服务器端内部做请求转发会造成filter失效
            response.sendRedirect("Query");
        }else {//登录失败
            request.setAttribute("nametip","用户不存在或");
            request.setAttribute("passtip","密码错误！");
            request.setAttribute("username",username);
            request.setAttribute("password",password);
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}