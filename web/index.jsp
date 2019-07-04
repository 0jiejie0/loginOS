<%--
  Created by IntelliJ IDEA.
  User: 0结界0
  Date: 2019/3/27 027
  Time: 8:09:26
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
<div>
    <jsp:include page="header.jsp"/>
    <div class="medium">
        <div class="left"><img id="headport" src="images/bj.jpeg"></div>
        <div class="right">
            <form action="Login" method="post" onsubmit="return sign()">
                <div class="rleft">用户名</div>
                <div class="rright">
                    <div class="rrightl"><input ${nametip==null?"":"style=\"border-color:red;border-width:2px\""}
                            id="username" onblur="checkname()" onfocus="typename()" class="inputstyle"
                            name="username" type="text" placeholder=" 请输入用户名" value="${username}">
                    </div>
                    <div class="rrightr"><span id="nametip">${nametip}</span></div>
                </div>

                <div class="rleft" align="center">密&nbsp;&nbsp;&nbsp;码</div>
                <div class="rright">
                    <div class="rrightl"><input ${passtip==null?"":"style=\"border-color:red;border-width:2px\""}
                            id="password" onblur="checkpasswd()" onfocus="typepasswd()" class="inputstyle"
                            name="password" type="password" placeholder=" 请输入密码" value="${password}">
                    </div>
                    <div class="rrightr"><span id="passtip">${passtip}</span></div>
                </div>

                <div class="rcenter"><input id="btn_sub" name="submit" type="submit" value="登   录"></div>

                <div class="rleft" id="forgot"><a class="fgotorrgst" href="#">忘记密码?</a></div>
                <div class="rright" id="regist"><a class="fgotorrgst" href="#">新用户注册</a></div>
            </form>
        </div>
    </div>
    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>
