<%--
  Created by IntelliJ IDEA.
  User: 0结界0
  Date: 2019/4/8 008
  Time: 8:34:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="bean.Student" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>201745080041解雪界</title>
    <link rel="stylesheet" type="text/css" href=<%=request.getContextPath()+"/CascadingStyleSheets/whole.css"%>>
    <link rel="stylesheet" type="text/css" href=<%=request.getContextPath()+"/CascadingStyleSheets/manage.css"%>>
    <link rel="stylesheet" type="text/css" href=<%=request.getContextPath()+"/CascadingStyleSheets/result.css"%>>
    <script type="text/javascript" src=<%=request.getContextPath() + "/JavaScript/manage.js"%>></script>
</head>
<%
    Student add = (Student) request.getAttribute("add");
    Object addtip = request.getAttribute("addtip");
%>
<%--el表达式不能用+直接连接字符串，所以大量字符串拼接仍然沿用jsp的java片段
    数据展示主要用el和jstl
--%>
<body <%=addtip == null ? "" : add != null ? (" onload=\"toadd(\'" +
        add.getName() + "\',\'" +
        add.getGender() + "\',\'" +
        add.getMajor() + "\',\'" +
        add.getDepartment() + "\',\'" +
        addtip.toString() + "\',\'" +
        request.getContextPath() + "\')\"") :
        " onload=\"toadd(\'" + addtip.toString() + "\',\'" + request.getContextPath() + "/Add\')\""%>>
<div>
    <jsp:include page="../header.jsp"/>
    <div class="medium">
        <div class="query">
            <c:set var="que" value="${queryresult.student}"/>
            <a <%=" onfocus=toadd(\"" + request.getContextPath() + "/Add\")"%> href="#add_name">
                <img alt="添加学生信息" title="添加学生信息" src=<%=request.getContextPath()+"/images/add.png"%>></a>
            <form id="query" action=<%=request.getContextPath()+"/Query"%> method="post"
                  onsubmit="return querynotnull()">
                <input id="que_name" type="text" name="quename" placeholder=" 姓名" onchange="query_submit()"
                       value="${que.name}">
                <select id="que_gender" name="quegender" onchange="query_submit()">
                    <option value="">性别</option>
                    <option value="男" ${"男".equals(que.gender)? " selected=\"selected\"" : ""}>
                        男
                    </option>
                    <option value="女" ${"女".equals(que.gender)? " selected=\"selected\"" : ""}>
                        女
                    </option>
                </select>
                <input id="que_major" type="text" name="quemajor" placeholder=" 专业" onchange="query_submit()"
                       value="${que.major}">
                <input id="que_depart" name="quedepart" placeholder=" 学院" onchange="query_submit()"
                       value="${que.department}">
                <input id="que_current_page" name="quecurrentpage" type="hidden"
                       value="${queryresult.currentPage}">
                <input id="que_rec_per_page" name="querecperpage" type="hidden"
                       value="${queryresult.recPerPage}">
                <input id="queall_sub" type="button" value="查看所有" onclick="query_all()">
            </form>
        </div>
        <c:set var="quelist" value="${queryresult.studentList}"/>
        <c:set var="size" value="${quelist.size()}"/>
        <c:set var="n" value="${(queryresult.currentPage-1)*queryresult.recPerPage}"/><%--这个东西让人很难受，不支持自增运算符--%>
        <c:if test="${size<=0}">
            <h3>哎呀！没有找到您想要的信息呢~~</h3>
        </c:if>
        <c:if test="${size>0}">
            <table class="result">
                <tr>
                    <th class="seque">序号</th>
                    <th class="name">姓名</th>
                    <th class="gender">性别</th>
                    <th class="major">专业</th>
                    <th class="department">学院</th>
                    <th class="option">操作</th>
                </tr>
                <c:forEach var="stu" items="${quelist}">
                    <tr class="lines">
                        <td class="seque">${n=n+1}</td>
                        <td class="name">${stu.name}</td>
                        <td class="gender">${stu.gender}</td>
                        <td class="major">${stu.major}</td>
                        <td class="department">${stu.department}</td>
                        <td class="option">
                            <form class="left">
                                <input type="button" value="修改"
                                       onclick="alter(${n},'${stu.name}','${stu.gender}','${stu.major}','${stu.department}',
                                           ${stu.id},'<%=request.getContextPath()%>')">
                            </form>
                            <form class="right" action="<%=request.getContextPath()+"/Delete"%>" method="post">
                                <input name="dltid" type="hidden" value="${stu.id}">
                                <input type="submit" value="删除">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <div class="turn">
                共找到${queryresult.recordNum}条记录，显示<input id="rec_per_page" type="number" onchange="page_change()"
                                                        value="${queryresult.recPerPage}">条/页
                共${queryresult.pageNum}页
                当前第<input id="current_page" type="number" onchange="page_jump()"
                          value="${queryresult.currentPage}">页
                <input type="button" value="第一页" onclick="page_turn(1)">
                <input type="button" value="上一页" onclick="page_turn(${queryresult.currentPage-1})">
                <input type="button" value="下一页" onclick="page_turn(${queryresult.currentPage+1})">
                <input type="button" value="最末页" onclick="page_turn(${queryresult.pageNum})">
            </div>
        </c:if>
        <tr class="altline">
            <%--                                    <td class="seque"></td>--%>
            <%--                                    <td class="name">--%>
            <%--                                        <input type="text" id="alt_name" value="">--%>
            <%--                                    </td><td class="gender">--%>
            <%--                                        <select id="alt_gender">--%>
            <%--                                            <option value="男">男</option>--%>
            <%--                                            <option value="女">女</option>--%>
            <%--                                        </select>--%>
            <%--                                    </td>--%>
            <%--                                    <td class="major"><input type="text" value="" id="alt_major"></td>--%>
            <%--                                    <td class="department"><input type="text" id="alt_depart" value=""></td>--%>
            <%--                                    <td class="option">--%>
            <%--                                        <form id="alter" action="Alter" method="post">--%>
            <%--                                            <input type="hidden" name="altname" id="alt_name_hid" value="">--%>
            <%--                                            <input type="hidden" name="altgender" value="" id="alt_gender_hid">--%>
            <%--                                            <input type="hidden" name="altmajor" value="" id="alt_major_hid">--%>
            <%--                                            <input type="hidden" name="altdepart" id="alt_depart_hid" value="">--%>
            <%--                                            <input type="hidden" name="altid" id="alt_id" value="">--%>
            <%--                                            <input id="alt_sub" type="button" value="确认修改" onclick="alter_submit()">--%>
            <%--                                        </form>--%>
            <%--                                    </td>--%>
        </tr>
        <div class="add" id="add">
            <%--<span>新增：</span>--%>
            <%--<form action="../Add" method="post" onsubmit="return check()">--%>
            <%--<input id="add_name" type="text" name="addname" placeholder=" 姓名" onfocus="input_add('name')" onblur="check_add('name')">--%>
            <%--<select id="add_gender" name="addgender" onfocus="input_add('gender')" onblur="check_add('gender')">--%>
            <%--<option value="">性别</option>--%>
            <%--<option value="男">男</option>--%>
            <%--<option value="女">女</option>--%>
            <%--</select>--%>
            <%--<input id="add_major" type="text" name="addmajor" placeholder=" 专业" onfocus="input_add('major')" onblur="check_add('major')">--%>
            <%--<input id="add_depart" type="text" name="adddepart" placeholder=" 学院" onfocus="input_add('depart')" onblur="check_add('depart')">--%>
            <%--<input id="add_sub" type="submit" value="确认添加">--%>
            <%--</form>--%>
            <%--<div><span id="add_tip"></span></div>--%>
        </div>
    </div>
    <jsp:include page="../footer.jsp"/>
</div>
</body>
</html>