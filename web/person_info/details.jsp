<%--
  Created by IntelliJ IDEA.
  User: 0结界0
  Date: 2019/4/8 008
  Time: 8:34:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>201745080041解雪界</title>
    <link rel="stylesheet" type="text/css" href=<%=request.getContextPath()+"/CascadingStyleSheets/whole.css"%>>
    <link rel="stylesheet" type="text/css" href=<%=request.getContextPath()+"/CascadingStyleSheets/manage.css"%>>
    <link rel="stylesheet" type="text/css" href=<%=request.getContextPath()+"/CascadingStyleSheets/result.css"%>>
    <script type="text/javascript" src=<%=request.getContextPath() + "/JavaScript/manage.js"%>></script>
</head>
<body>
<div>
    <jsp:include page="../header.jsp"/>
    <div style="width: 500px; margin: 100px auto">
        姓名：${requestScope.student.name}
        <br><br>
        性别：${requestScope.student.gender}
        <br><br>
        专业：${requestScope.student.major}
        <br><br>
        院系：${requestScope.student.department}
    </div>
    <jsp:include page="../footer.jsp"/>
</div>
</body>
</html>