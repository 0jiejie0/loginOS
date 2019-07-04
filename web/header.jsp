<%--
  Created by IntelliJ IDEA.
  User: 0结界0
  Date: 2019/4/16 016
  Time: 20:50:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>201745080041解雪界</title>
    <link rel="stylesheet" type="text/css" href="CascadingStyleSheets/whole.css">
    <script type="text/javascript" src="JavaScript/login.js"></script>
</head>
<body>
<div class="top">
    <div class="logo"><img alt="山东体育学院" id="logo" src=<%=request.getContextPath()+"/images/logo.png"%>></div>
    <div class="title">在线考试系统</div>
    <div class="user">
        ${userinfo==null?"201745080041+解雪界":"欢迎您，".concat(userinfo.username).concat("！<a href=\"LogOut\">退出登录</a>")}
<%--        ‘+’不能做字符串拼接，el运算符可能试图将两边表达式值转化成数字，会产生异常，用concat（）方法连接字符串--%>
    </div>
    <hr/>
</div>
</body>
</html>
