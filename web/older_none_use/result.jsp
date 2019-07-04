<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.ResultSet" %>
<%--
  Created by IntelliJ IDEA.
  User: 0结界0
  Date: 2019/4/20 020
  Time: 11:44:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>201745080041解雪界</title>
    <link rel="stylesheet" type="text/css" href=<%=request.getContextPath()+"/CascadingStyleSheets/whole.css"%>>
    <link rel="stylesheet" type="text/css" href=<%=request.getContextPath()+"/CascadingStyleSheets/result.css"%>>
    <script type="text/javascript" src="result.js"></script>
</head>
<body>

<%
    String theader = "<tr>\n" +
            "<th class=\"seque\">序号</th>\n" +
            "<th class=\"name\">姓名</th>\n" +
            "<th class=\"gender\">性别</th>\n" +
            "<th class=\"major\">专业</th>\n" +
            "<th class=\"department\">学院</th>\n" +
            "<th class=\"option\">操作</th>\n" +
            "</tr>\n";
    ResultSet resultSet = (ResultSet) session.getAttribute("queryresult");
    try {
        //刷新时将resultset光标移到第一行，否则无法显示
        if (resultSet != null) {
            resultSet.beforeFirst();
        }
        if (resultSet != null && resultSet.next()) {
            out.print(theader);
            int n = 1;
            do {
                StringBuilder line = new StringBuilder();
                String name = resultSet.getString("name");
                String gender = resultSet.getString("gender");
                String major = resultSet.getString("major");
                String department = resultSet.getString("department");
                line.append("<tr style=\"background-color: #" + ((n % 2) == 0 ? "e9ebf5" : "cfd5ea") +
                        "\" >        <td class=\"seque\">\n" + n++ +
                        "                </td><td class=\"name\">" + name +
                        "                </td><td class=\"gender\">\n" + gender +
                        "                </td><td class=\"major\">\n" + major +
                        "                </td><td class=\"department\">\n" + department +
                        "                </td><td class=\"option\">\n" +
                        "                    <a class=\"left\">修改</a>\n" +
                        "                    <form class=\"right\" action=\"" + request.getContextPath() +
                        "/Delete\" method=\"post\"><input name=\"dltname\" type=\"hidden\" value=\"" + name +
                        "\"><input type=\"submit\" value=\"删除\"></form>" +
                        "                </td></tr>"
                );
                out.print(line);
            } while (resultSet.next());
        } else {
            out.print("哎呀！没有找到您想要的信息呢~~");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
//                if (resultSet != null)
//                    resultSet.close();
//                session.removeAttribute("queryresult");
%>
</body>
</html>
