function checkname() {
    var tip = document.getElementById("nametip");
    var input = document.getElementById("username");
    var name = input.value;
    var length = name.length;
    warn(input, tip);
    if (length == 0) {
        tip.innerText = "请输入用户名！";
        return false;
    } else if (length > 20) {
        tip.innerText = "用户名过长！";
        return false;
    } else if (new RegExp("[ ]").test(name)) {
        tip.innerText = "用户名不得含空格！";
        return false;
    } else {
        type(input, tip);
        tip.innerText = "";
        return true;
    }
}

function typename() {
    var tip = document.getElementById("nametip");
    tip.innerText = "20个字符以内";
    type(document.getElementById("username"), tip);
}

function checkpasswd() {
    var tip = document.getElementById("passtip");
    var input = document.getElementById("password");
    var password = input.value;
    var length = password.length;
    warn(input, tip);
    if (length == 0) {
        tip.innerText = "请输入密码！";
        return false;
    } else if (length < 6) {
        tip.innerText = "密码过短！";
        return false;
    } else if (length > 18) {
        tip.innerText = "密码过长！";
        return false;
    } else if (new RegExp("[ ]").test(password)) {
        tip.innerText = "密码不能有空格！";
        return false;
    } else {
        type(input, tip);
        tip.innerText = "";
        return true;
    }
}

function typepasswd() {
    var tip = document.getElementById("passtip");
    tip.innerText = "6~18位";
    type(document.getElementById("password"), tip);
}

function type(input, tip) {
    input.style.borderColor = "#1f65ff";
    input.style.borderWidth = "1px";
    tip.style.color = "gray";
}

function warn(input, tip) {
    input.style.borderColor = "red";
    input.style.borderWidth = "2px";
    tip.style.color = "red";
}

function sign() {
    if (!(checkname() && checkpasswd())) {
        return false;
    }
    return true;
}