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

@WebServlet(name = "Alter", value = {"/Alter"})
public class Alter extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("altid");
        String name = request.getParameter("altname");
        String gender = request.getParameter("altgender");
        String major = request.getParameter("altmajor");
        String department = request.getParameter("altdepart");
        Student student = new Student();
        student.setId(Integer.parseInt(id));
        student.setName(name);
        student.setGender(gender);
        student.setMajor(major);
        student.setDepartment(department);
        StudentDao studentDao = DaoFactory.getStudentDaoImpl();
        studentDao.update(student);
        response.sendRedirect("Query");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}