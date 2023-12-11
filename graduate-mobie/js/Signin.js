// 地址逆解析，并发送经纬度数据到后端
function GetLocation() {
    var geoc = new BMap.Geocoder();
    geoc.getLocation(point, function (rs) {
        var addComp = rs.addressComponents;
        var address = addComp.province + addComp.city + addComp.district + addComp.street + addComp.streetNumber;

        // 创建一个包含经纬度和地址的对象
        var locationData = {
            latitude: point.lat,
            longitude: point.lng,
            address: address
        };

        // 发送HTTP请求，将经纬度数据传递给后端
        sendLocationData(locationData);
    });
}

// 发送经纬度数据到后端
function sendLocationData(locationData) {
    // 使用Ajax或Fetch等技术发送HTTP请求
    // 这里是一个使用Fetch发送POST请求的示例
    fetch('/your-backend-url', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(locationData)
    })
        .then(response => response.json())
        .then(data => {
            // 在这里处理后端返回的响应数据
            console.log(data);
            // 可以根据后端返回的数据进行相应的操作
        })
        .catch(error => {
            // 在这里处理错误
            console.error('Error:', error);
        });
}