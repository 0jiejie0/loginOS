function querynotnull() {
    var name = document.getElementById("que_name").value;
    var gender = document.getElementById("que_gender").value;
    var major = document.getElementById("que_major").value;
    var department = document.getElementById("que_depart").value;
    if (name.length == 0 && gender.length == 0 && major.length == 0 && department.length == 0) {
        return false;
    }
    return true;
}

//查询全部
function query_all() {
    document.getElementById("que_name").value = "";
    document.getElementById("que_gender").value = "";
    document.getElementById("que_major").value = "";
    document.getElementById("que_depart").value = "";
    document.getElementById("que_current_page").value = 1;//重置页码
    document.getElementById("query").submit();
}

//查询部分信息
function query_submit() {
    document.getElementById("que_current_page").value = 1;//修改条件后，重置页码
    if (querynotnull()) {
        document.getElementById("query").submit();
    }
}

//每页显示记录数变化
function page_change() {
    var que_rec_per_page = document.getElementById("rec_per_page").value;
    if (!only_num(que_rec_per_page)) {//不能完全保证数据合法，但降低服务端压力
        que_rec_per_page = "10";
    }
    document.getElementById("que_rec_per_page").value = que_rec_per_page;
    document.getElementById("que_current_page").value = 1;
    document.getElementById("query").submit();
}

//手动输入页数
function page_jump() {
    page_turn(document.getElementById("current_page").value);
}

//跳到指定页面
function page_turn(num) {//服务端已经做过数字合法性检查，这里只部分保证为数字
    if (!only_num(num)) {
        num = 1;
    }
    document.getElementById("que_current_page").value = num;
    document.getElementById("query").submit();
}

function toadd() {
    var exist = document.getElementById("add_name");
    if (exist != null) {
        window.location.href = "#add_name";
        return
    }
    document.getElementById("add").innerHTML = "" +
        "            <span>新增：</span>\n" +
        "            <form action=\"" + (arguments.length == 1 ? arguments[0] : arguments.length == 2 ? arguments[1] : arguments[5]) +
        "\" method=\"post\" onsubmit=\"return check()\">\n" +
        "                <input id=\"add_name\" type=\"text\" name=\"addname\" placeholder=\" 姓名\" onclick=" +
        " \"input_add('name')\" onblur=\"check_add('name')\">\n" +
        "                <select id=\"add_gender\" name=\"addgender\" onclick=\"input_add('gender')\" onblur=" +
        "\"check_add('gender')\">\n" +
        "                    <option value=\"\">性别</option>\n" +
        "                    <option value=\"男\">男</option>\n" +
        "                    <option value=\"女\">女</option>\n" +
        "                </select>\n" +
        "                <input id=\"add_major\" type=\"text\" name=\"addmajor\" placeholder=\" 专业\" onclick=" +
        "\"input_add('major')\" onblur=\"check_add('major')\">\n" +
        "                <input id=\"add_depart\" type=\"text\" name=\"adddepart\" placeholder=\" 学院\" onclick=" +
        "\"input_add('depart')\" onblur=\"check_add('depart')\">\n" +
        "                <input id=\"add_sub\" type=\"submit\" value=\"确认添加\">\n" +
        "            </form>\n" +
        "            <div><span id=\"add_tip\"></span></div>\n";
    var tip = document.getElementById("add_tip");
    if (arguments.length == 2) {
        tip.style.color = "green";
        tip.innerText = arguments[0];
    } else if (arguments.length == 6) {
        var name = document.getElementById("add_name");
        var gender = document.getElementById("add_gender");
        var major = document.getElementById("add_major");
        var depart = document.getElementById("add_depart");
        name.value = arguments[0];
        gender.value = arguments[1];
        major.value = arguments[2];
        depart.value = arguments[3];
        tip.innerText = arguments[4];
        warn(document.getElementById("add_name"));
    }
    window.location.href = "#add_name";
}

function check_add(type) {
    var input = document.getElementById("add_" + type);
    var name = input.value;
    var length = name.length;
    var tip = document.getElementById("add_tip");
    if (length == 0) {
        warn(input);
        tip.innerText =
            type == "name" ? "请输入学生姓名！" : type == "gender" ? "请选择性别！" :
                type == "major" ? "请输入主修专业！" : type == "depart" ? "请输入主修专业！" : "aaaa";
        return false;
    } else if (type == "name" && new RegExp("[ ]").test(name)) {
        warn(input);
        tip.innerText = "姓名不得含空格！"
    }
    return true;
}

function input_add(type) {
    var input = document.getElementById("add_" + type);
    var tip = document.getElementById("add_tip");
    input.style.borderColor = "#1f65ff";
    input.style.borderWidth = "1px";
    tip.innerText = "";
}

function check() {
    if (check_add("name") && check_add("gender") && check_add("major") && check_add("depart")) {
        return true;
    }
    return false;
}

function alter(seque, name, gender, major, department, id, href) {
    var exist = document.getElementById("alt_sub");
    if (exist != null) {
        return;
    }
    var line = document.getElementsByClassName("lines").item(seque - 1);
    line.innerHTML =
        "                <td class=\"seque altline\">" + seque + "</td>\n" +
        "                <td class=\"name altline\"><input type=\"text\" id=\"alt_name\" ></td>\n" +
        "                <td class=\"gender altline\">\n" +
        "                    <select id=\"alt_gender\">\n" +
        "                        <option value=\"男\">男</option>\n" +
        "                        <option value=\"女\">女</option>\n" +
        "                    </select>\n" +
        "                </td>\n" +
        "                <td class=\"major altline\"><input type=\"text\" id=\"alt_major\"></td>\n" +
        "                <td class=\"department altline\"><input type=\"text\" id=\"alt_depart\"></td>\n" +
        "                <td class=\"option altline\" id=\"alt_option\">\n" +
        "                    <form id=\"alter\" action=\"" + href + "/Alter\" method=\"post\">\n" +
        "                        <input type=\"hidden\" name=\"altname\" id=\"alt_name_hid\" value=\"\">\n" +
        "                        <input type=\"hidden\" name=\"altgender\" value=\"\" id=\"alt_gender_hid\">\n" +
        "                        <input type=\"hidden\" name=\"altmajor\" value=\"\" id=\"alt_major_hid\">\n" +
        "                        <input type=\"hidden\" name=\"altdepart\" id=\"alt_depart_hid\" value=\"\">\n" +
        "                        <input type=\"hidden\" name=\"altid\" id=\"alt_id\" value=\"\">\n" +
        "                        <input id=\"alt_sub\" type=\"button\" value=\"确认修改\" onclick=\"alter_submit()\">\n" +
        "                    </form>\n" +
        "                </td>";
    document.getElementById("alt_id").value = id;
    document.getElementById("alt_name").value = name;
    document.getElementById("alt_gender").value = gender;
    document.getElementById("alt_major").value = major;
    document.getElementById("alt_depart").value = department;
}

function alter_submit() {
    if (!alter_notnull()) {
        return;
    }
    var name = document.getElementById("alt_name").value;
    var gender = document.getElementById("alt_gender").value;
    var major = document.getElementById("alt_major").value;
    var department = document.getElementById("alt_depart").value;
    document.getElementById("alt_name_hid").value = name;
    document.getElementById("alt_gender_hid").value = gender;
    document.getElementById("alt_major_hid").value = major;
    document.getElementById("alt_depart_hid").value = department;

    document.getElementById("alter").submit();
}

function alter_notnull() {
    var name = document.getElementById("alt_name").value;
    var gender = document.getElementById("alt_gender").value;
    var major = document.getElementById("alt_major").value;
    var department = document.getElementById("alt_depart").value;
    if (name.length == 0 || gender.length == 0 || major.length == 0 || department.length == 0 ||
        new RegExp("[ ]").test(name + gender + major + department)) {
        return false;
    }
    return true;
}

function warn(input) {
    input.style.borderColor = "red";
    input.style.borderWidth = "2px";
}

function only_num(num) {
    return new RegExp("^[0-9]+$").test(num);
}