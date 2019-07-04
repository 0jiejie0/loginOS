<%--
  Created by IntelliJ IDEA.
  User: 0结界0
  Date: 2019/4/3 003
  Time: 0:07:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="util.DbUtil" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.awt.*" %>
<%@ page import="java.sql.SQLException" %>
<html>
<head>
    <title>login action</title>
</head>
<body>
<%
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    String sql;
    ResultSet resultSet = null;
    sql = "select fd_username from tb_users where fd_username = ?";
    resultSet = DbUtil.executeQuery(sql, username);
    if (resultSet == null || !resultSet.next()) {
        request.setAttribute("nametip", "用户名不存在！");
    } else {
        sql += " and fd_password = ?";
        resultSet = DbUtil.executeQuery(sql, username, password);
        if (resultSet == null || !resultSet.next()) {
            request.setAttribute("passtip", "密码错误！");
        } else {
            DbUtil.close();
//            wait(2000);
            session.setAttribute("username", username);
            response.sendRedirect("manage.jsp");
            return;
        }
    }
    DbUtil.close();

    request.setAttribute("username", username);
    request.setAttribute("password", password);
    request.getRequestDispatcher("index.jsp").forward(request, response);
%>
</body>
</html>