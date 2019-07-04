package servlet;

import bean.Student;
import dao.StudentDao;
import daoImpl.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Delete", value = "/Delete")
public class Delete extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("dltid");
        Student student = new Student();
        student.setId(Integer.parseInt(id));
        StudentDao studentDao = DaoFactory.getStudentDaoImpl();
        studentDao.delete(student);
        response.sendRedirect("Query");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}