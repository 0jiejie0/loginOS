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

@WebServlet(name = "PersonDetailServlet", value = "/PersonDetailServlet")
public class PersonDetailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("userinfo");
        Student student = new Student();
        student.setId(user.getId());
        StudentDao studentDao = DaoFactory.getStudentDaoImpl();
        student = studentDao.find(student);
        request.setAttribute("student", student);
        request.getSession().setAttribute("student", student);
        request.getRequestDispatcher("person_info/details.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}