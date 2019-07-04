package servlet;

import bean.Student;
import bean.User;
import dao.StudentDao;
import daoImpl.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Add", value = "/Add")
public class Add extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("addname");
        String gender = request.getParameter("addgender");
        String major = request.getParameter("addmajor");
        String department = request.getParameter("adddepart");
//        PrintWriter out = response.getWriter();
//        out.println(name);
//        out.println(gender);
//        out.println(major);
//        out.println(department);
        Student student = new Student();
        student.setName(name);
        student.setGender(gender);
        student.setMajor(major);
        student.setDepartment(department);
        User user = new User();
        user.setPassword("123123");//默认密码123123
        StudentDao studentDao = DaoFactory.getStudentDaoImpl();
        int result = studentDao.add(student, user);
        String tip;
        if (result == 1) {
            tip = "添加成功！";
        } else {
            tip = "添加失败！";
            request.setAttribute("add", student);//原输入信息返回页面
        }
        request.setAttribute("addtip", tip);
        request.getRequestDispatcher("Query").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}