var baseUrl="http://127.0.0.1:8089/api"
//加载jqurey
//ajax在什么时候开始执行
//页面加载完成以后 才请求数据
//页面加载完成后执行
window.onload=function (){
    //调用 初始数据
    iniData();
}
//初始化
function iniData(){
    //加载初始数据
    $.ajax({
        url: baseUrl+'/findStudentById?id=1',
        type: 'get',
        // 设置的是请求参数
        data: {        },
        dataType: 'json', // 用于设置响应体的类型 注意 跟 data 参数没关系！！！
        success: function (res) {
            var studentId = document.getElementById("studentId");
            // 一旦设置的 dataType 选项，就不再关心 服务端 响应的 Content-Type 了
            // 客户端会主观认为服务端返回的就是 JSON 格式的字符串
            var data = res.data;
            var htmlVal = "<div>${data.sNumber}</div>";
            studentId.innerHTML=htmlVal;
        }
    });
}