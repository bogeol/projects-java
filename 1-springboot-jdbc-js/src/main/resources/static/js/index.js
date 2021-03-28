var allUserList = [];

function selectAll_ul_li() {
    // clear list
    beforeSelectAll();

    // xhr
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:12345/user/selectAll");
    xhr.send();
    xhr.onload = function () {
        if (xhr.status == 200) {
            var res = xhr.response;
            var resobj = JSON.parse(res);
            allUserList = [];
            allUserList = JSON.parse(res);
            var userListDiv = document.querySelector("#userList");
            resobj.forEach(e => {
                let ul = document.createElement("ul");
                let id = document.createElement("li");
                let name = document.createElement("li");
                let age = document.createElement("li");
                let phone = document.createElement("li");
                let email = document.createElement("li");
                id.innerText = e.id;
                name.innerText = e.name;
                age.innerText = e.age;
                phone.innerText = e.phone;
                email.innerText = e.email;
                ul.appendChild(id);
                ul.appendChild(name);
                ul.appendChild(age);
                ul.appendChild(phone);
                ul.appendChild(email);
                userListDiv.appendChild(ul);
            });
        } else {
            alert("error")
        }
    };
}

function selectAll_ul_input() {
    // clear list
    beforeSelectAll();

    // xhr
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:12345/user/selectAll");
    xhr.send();
    xhr.onload = function () {
        if (xhr.status == 200) {
            var res = xhr.response;
            var resobj = JSON.parse(res);
            allUserList = [];
            allUserList = JSON.parse(res);
            console.log(resobj);
            var userListDiv = document.querySelector("#userList");
            resobj.forEach(e => {
                let ul = document.createElement("ul");
                let li_id = document.createElement("li");
                let li_name = document.createElement("li");
                let li_age = document.createElement("li");
                let li_phone = document.createElement("li");
                let li_email = document.createElement("li");
                let li_buttons = document.createElement("li");
                let id = document.createElement("input");
                let name = document.createElement("input");
                let age = document.createElement("input");
                let phone = document.createElement("input");
                let email = document.createElement("input");
                let update_button = document.createElement("button");
                let delete_button = document.createElement("button");
                id.setAttribute("value", e.id);
                id.setAttribute("disabled", "true");
                id.setAttribute("onchange", "inputChange(this)")
                name.setAttribute("value", e.name);
                name.setAttribute("onchange", "inputChange(this)")
                age.setAttribute("value", e.age);
                age.setAttribute("onchange", "inputChange(this)")
                phone.setAttribute("value", e.phone);
                phone.setAttribute("onchange", "inputChange(this)")
                email.setAttribute("value", e.email);
                email.setAttribute("onchange", "inputChange(this)")
                update_button.innerText = "update";
                delete_button.innerText = "delete";
                update_button.setAttribute("onclick", "updateUser(this)");
                delete_button.setAttribute("onclick", "deleteUser(this)");
                li_id.appendChild(id);
                li_name.appendChild(name);
                li_age.appendChild(age);
                li_phone.appendChild(phone);
                li_email.appendChild(email);
                li_buttons.appendChild(update_button);
                li_buttons.appendChild(delete_button);
                ul.appendChild(li_id);
                ul.appendChild(li_name);
                ul.appendChild(li_age);
                ul.appendChild(li_phone);
                ul.appendChild(li_email);
                ul.appendChild(li_buttons);
                userListDiv.appendChild(ul);
            });
        } else {
            alert("error")
        }
    };
}

function beforeSelectAll() {
    var userListDiv = document.querySelector("#userList");
    userListDiv.innerHTML = "";
}

function random() {
    // clear list
    beforeRandom();

    // xhr
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:12345/user/random");
    xhr.send();
    xhr.onload = function () {
        if (xhr.status == 200) {
            var res = xhr.response;
            var resobj = JSON.parse(res);
            console.log(resobj);
            var randomListDiv = document.querySelector("#randomList");
            var ul = document.createElement("ul");
            resobj.forEach(e => {
                let li = document.createElement("li");
                li.innerText = e;
                ul.appendChild(li);
            });
            randomListDiv.appendChild(ul);
        } else {
            alert("error")
        }
    };
}

function beforeRandom() {
    var randomListDiv = document.querySelector("#randomList");
    randomList.innerHTML = "";
}

function updateUser(element) {
    var ul = element.parentNode.parentNode;
    var id = ul.childNodes[0].firstChild.getAttribute("value");
    var name = ul.childNodes[1].firstChild.getAttribute("value");
    var age = ul.childNodes[2].firstChild.getAttribute("value");
    var phone = ul.childNodes[3].firstChild.getAttribute("value");
    var email = ul.childNodes[4].firstChild.getAttribute("value");

    var flag_duplicate = false;
    if (allUserList != null && allUserList != "") {
        for (let i = 0; i < allUserList.length; i++) {
            let e = allUserList[i];
            if (e.id == id) {
                if (e.id == id && e.name == name && e.age == age && e.phone == phone && e.email == email) {
                    alert("duplicate");
                    return;
                }
                break;
            }
        }
    }

    if (confirm("update user: id=" + id)) {
        // xhr
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "http://localhost:12345/user/update", true);
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.send(JSON.stringify({
            id: id,
            name: name,
            age: age,
            phone: phone,
            email: email
        }));
        xhr.onload = function () {
            if (xhr.status == 200) {
                var res = xhr.response;
                if (res == "true") {
                    alert("update successful");
                    window.location.reload();
                } else {
                    alert("update error");
                }
            } else {
                alert("response error");
            }
        };

    } else {
        alert("cancel update");
    }


}

// js获取当前点击元素[click处传递this就行]
function deleteUser(element) {
    var ul = element.parentNode.parentNode;
    var id = ul.childNodes[0].firstChild.getAttribute("value");

    if (confirm("delete user: id = " + id)) {
        // xhr
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "http://localhost:12345/user/delete");
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");// [ url params / json params ]
        xhr.send("id=" + id);
        xhr.onload = function () {
            if (xhr.status == 200) {
                var res = xhr.response;
                console.log(typeof res); // response: String [not Boolean]
                if (res == "true") {
                    alert("delete successful");
                    window.location.reload();
                } else {
                    alert("delete error");
                }
            } else {
                alert("response error")
            }
        };
    } else {
        alert("cancel delete")
    }

}

function addUser() {
    alert("addUser");
}

function createNewUserDiv() {
    let maxId = 0;
    if (allUserList != null && allUserList.length != 0) {
        maxId = allUserList[allUserList.length - 1].id + 1;
    }
    let newUserDiv = document.querySelector("#newUser");
    newUserDiv.innerHTML = "";
    let ul = document.createElement("ul");
    let li_id = document.createElement("li");
    let li_name = document.createElement("li");
    let li_age = document.createElement("li");
    let li_phone = document.createElement("li");
    let li_email = document.createElement("li");
    let li_buttons = document.createElement("li");
    let label_id = document.createElement("label");
    let input_id = document.createElement("input");
    let label_name = document.createElement("label");
    let input_name = document.createElement("input");
    let label_age = document.createElement("label");
    let input_age = document.createElement("input");
    let label_phone = document.createElement("label");
    let input_phone = document.createElement("input");
    let label_email = document.createElement("label");
    let input_email = document.createElement("input");
    let save_button = document.createElement("button");
    let cancel_button = document.createElement("button");
    label_id.innerText = "id : ";
    label_name.innerText = "name : ";
    label_age.innerText = "age : ";
    label_phone.innerText = "phone : ";
    label_email.innerText = "email : ";
    save_button.innerText = "save";
    cancel_button.innerText = "cancel";
    input_id.setAttribute("value", maxId);
    input_id.setAttribute("disabled", "true");

    input_id.setAttribute("onchange", "inputChange(this)")
    input_name.setAttribute("onchange", "inputChange(this)")
    input_age.setAttribute("onchange", "inputChange(this)")
    input_phone.setAttribute("onchange", "inputChange(this)")
    input_email.setAttribute("onchange", "inputChange(this)")

    save_button.setAttribute("onclick", "saveNewUser()");
    cancel_button.setAttribute("onclick", "cancelNewUser()");
    li_id.appendChild(label_id);
    li_id.appendChild(input_id);
    li_name.appendChild(label_name);
    li_name.appendChild(input_name);
    li_age.appendChild(label_age);
    li_age.appendChild(input_age);
    li_phone.appendChild(label_phone);
    li_phone.appendChild(input_phone);
    li_email.appendChild(label_email);
    li_email.appendChild(input_email);
    li_buttons.appendChild(save_button);
    li_buttons.appendChild(cancel_button);
    ul.appendChild(li_id);
    ul.appendChild(li_name);
    ul.appendChild(li_age);
    ul.appendChild(li_phone);
    ul.appendChild(li_email);
    ul.appendChild(li_buttons);
    newUserDiv.appendChild(ul);
}

function saveNewUser() {
    var newUserDiv_ul = document.querySelector("#newUser ul");
    var id = newUserDiv_ul.childNodes[0].childNodes[1].getAttribute("value");
    var name = newUserDiv_ul.childNodes[1].childNodes[1].getAttribute("value");
    var age = newUserDiv_ul.childNodes[2].childNodes[1].getAttribute("value");
    var phone = newUserDiv_ul.childNodes[3].childNodes[1].getAttribute("value");
    var email = newUserDiv_ul.childNodes[4].childNodes[1].getAttribute("value");

    if (confirm("add new user: id=" + id)) {
        // xhr
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "http://localhost:12345/user/insert", true);
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.send(JSON.stringify({
            id: id,
            name: name,
            age: age,
            phone: phone,
            email: email,
            status: 1,
        }));
        xhr.onload = function () {
            if (xhr.status == 200) {
                var res = xhr.response;
                if (res == "true") {
                    alert("insert successful");
                    window.location.reload();
                } else {
                    alert("insert error");
                }
            } else {
                alert("response error");
            }
        };

    } else {
        alert("cancel add new user");
    }


}

function cancelNewUser() {
    let newUserDiv = document.querySelector("#newUser");
    newUserDiv.innerHTML = "";
}

function inputChange(element) {
    // element.value为此时输入框的值,但是input标签的value属性还是原来的没变[因为之前的js函数是通过input-value属性拿值的,所以这里动态修改属性]
    element.setAttribute("value", element.value);
}

selectAll_ul_input();
