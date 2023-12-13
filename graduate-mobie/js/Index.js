//登录按钮
window.onload = function() {
    document.getElementById('div_sid2').addEventListener('click', function() {
        document.querySelector('#div_sid2 input[type="submit"]').click();
    });
};

//获取验证码
function startCountDown() {
    var btn = document.getElementById('bt');
    btn.disabled = true; // 禁用按钮
    btn.style.color = '#ccc'; // 改变按钮颜色表示不可点击

    // 发送请求到后端接口
    fetch('http://localhost:8089/api/sendCode')
        .then(response => response.json())
        .then(data => {
            // 接收后端返回的数据
            console.log(data);

            // 存储后端发送的验证码uuid
            var uuid = data.uuid;
            localStorage.setItem('uuid', uuid); // 使用localStorage存储uuid
        })
        .catch(error => {
        // 发生错误时的处理
        console.error('Error:', error);
    });

    var count = 60; // 初始倒计时时间为60秒
    var timer = setInterval(function() {
        count--;
        btn.value = count + "s 后重新获取"; // 更新按钮上的文本

        if (count <= 0) {
            clearInterval(timer); // 清除定时器
            btn.value = "获取验证码"; // 恢复按钮文本
            btn.disabled = false; // 启用按钮
            btn.style.color = '#757575'; // 恢复按钮颜色
            count = 60; // 重置倒计时时间
        }
    }, 1000);
}

//发送数据给后端
function submitForm() {
    var form = document.getElementById('loginForm');
    var formData = new FormData(form);
    // 获取电话号码和验证码的输入框
    var usernameInput = document.getElementsByName('username')[0];
    var passwordInput = document.getElementsByName('password')[0];

    // 从localStorage中获取验证码uuid
    var uuid = localStorage.getItem('uuid');
    // 将phone、code和uuid添加到formData中
    formData.append('phone', usernameInput.value.trim());
    formData.append('code', passwordInput.value.trim());
    formData.append('uuid', uuid);

    fetch('http://localhost:8089/api/logins', {
        method: 'POST',
        body: formData
    })
        .then(response => response.json())
        .then(data => {
            // 接收后端返回的数据
            console.log(data);

            // 存储后端发送的验证码uuid和bStudent对象数据
            var token = data.token;
            var bStudent = data.bStudent;

            // 登录成功，存储token和bStudent到localStorage
            localStorage.setItem('token', token);
            localStorage.setItem('bStudent', JSON.stringify(bStudent));

            window.location.href = "./page/Signin.html";

            // TODO:其他需要处理的逻辑
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

// 监听浏览器关闭事件
window.onbeforeunload = function() {
    // 清除localStorage中存储的数据
    // localStorage.removeItem('uuid');
    // localStorage.removeItem('token');
    // localStorage.removeItem('bStudent');
    // 清除localStorage中存储的uuid
    localStorage.removeItem('uuid');
};