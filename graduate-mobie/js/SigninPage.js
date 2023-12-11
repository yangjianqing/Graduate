// 创建一个新的 XMLHttpRequest 对象
var xhr = new XMLHttpRequest();

// 设置请求参数和请求方式（GET 或 POST）
xhr.open("GET", "/Signin/GetId", true);

// 监听请求状态改变事件
xhr.onreadystatechange = function () {
    if (xhr.readyState === 4 && xhr.status === 200) {
        // 解析返回的 JSON 数据
        var response = JSON.parse(xhr.responseText);

        // 获取签到时间和签到地点
        for (var i = 0; i < response.length; i++) {
            var record = response[i];
            var time = record.time;
            var location = record.location;

            // 在页面上显示签到时间和签到地点
            var tableBody = document.getElementById("table-body");
            var row = tableBody.insertRow(-1);
            var timeCell = row.insertCell(0);
            var locationCell = row.insertCell(1);
            timeCell.innerHTML = time;
            locationCell.innerHTML = location;
        }
    }
};

// 发送请求
xhr.send();
