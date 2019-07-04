package servlet;

import bean.Page;
import bean.Student;
import dao.StudentDao;
import daoImpl.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

@WebServlet(name = "Query", value = "/Query")
public class Query extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Student student = new Student();
        Page page = new Page();
        student.setName(request.getParameter("quename"));
        student.setGender(request.getParameter("quegender"));
        student.setMajor(request.getParameter("quemajor"));
        student.setDepartment(request.getParameter("quedepart"));
        String querecperpage = request.getParameter("querecperpage");
        Pattern pattern = Pattern.compile("^\\d+$");
        if (querecperpage == null || !pattern.matcher(querecperpage).find()) {//保证数据是一个数字，防止后续转换异常
            querecperpage = "10";
        }
        page.setRecPerPage(Integer.parseInt(querecperpage));
        String quecurrentpage = request.getParameter("quecurrentpage");
        if (quecurrentpage == null || !pattern.matcher(quecurrentpage).find()) {//防止转换异常
            quecurrentpage = "1";
        }
        page.setCurrentPage(Integer.parseInt("".equals(quecurrentpage.trim()) ? "1" : quecurrentpage.trim()));
        page.setStudent(student);
        StudentDao studentDao = DaoFactory.getStudentDaoImpl();
        page = studentDao.find(page);
        request.setAttribute("queryresult", page);//查询结果传回客户端,page中含有查询条件
        request.getRequestDispatcher("manage_page/manage.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}