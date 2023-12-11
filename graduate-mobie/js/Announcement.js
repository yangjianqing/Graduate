// 获取公告数据
fetch("http://127.0.0.1:8089/api/announcement", {
    headers: {
        'Content-Type': 'application/json'
    }
}) // 向服务器端发送获取公告数据的请求
    .then(response => {
        return response.json(); // 将响应数据转换为 JSON 格式
    })
    .then(data => {
        // 根据数据渲染公告列表
        const listContainer = document.getElementById('announcement-list'); // 获取存放公告列表的容器元素
        data.forEach(info => {
            const listItem = document.createElement('div'); // 创建一个 div 元素用于存放单条公告信息
            listItem.classList.add('flex-col', 'mt-16', 'list-item_2'); // 添加样式类
            listItem.innerHTML = `
                <span class="self-start font_4 text_13" id="nTitle">${"#" + info.nTitle + "#"}</span>
                <span class="self-start font_3 text_14 mt-15" id="nContent">${info.nContent}</span>
                <div class="flex-row items-center self-stretch mt-15">
                    <img class="image_4" src="../img/InFo/chuangjianren.png" />
                    <span class="font text_15 ml-9" id="nPeople">${info.nPeople}</span>
                    <span class="font text_15 ml-9" id="nTime">${info.createTime}</span>
                </div>
            `;
            listContainer.appendChild(listItem); // 将单条公告信息添加到公告列表容器中
        });
    })
    .catch(error => {
        console.error('获取数据失败', error); // 捕获并打印获取数据失败的错误信息
    });





//实现搜索功能
function searchAnnouncements() {
    // 获取搜索框中的内容
    var searchText = document.getElementById('search-input').value;

    // 发送搜索请求给后端
    fetch('http://127.0.0.1:8089/api/search?keyword=' + searchText)
        .then(response => response.json())
        .then(data => {
            // 假设从后端获取到了 joinData 数据
            var joinData = data.data;
            renderAnnouncements(joinData,joinData,searchText); // 传入两个参数
        })
        .catch(error => {
            console.error('搜索失败:', error);
            alert('搜索内容不存在');
        });
}



// 渲染搜索到的内容
function renderAnnouncements(announcements,joinData,searchText) {
    var announcementList = document.getElementById('announcement-list');
    announcementList.innerHTML = ''; // 清空之前的搜索结果

    announcements.forEach(function (announcement, index) {
        var listItem = document.createElement('div');
        listItem.classList.add('flex-col', 'mt-16', 'list-item_2');

        var content = JSON.stringify(announcement); // 获取整个公告对象的内容
        listItem.textContent = content;

        var joinInfo = joinData[index]; // 获取对应的 join 数据
        // 高亮显示搜索关键词
        var regex = new RegExp(searchText, 'gi');
        var highlightedContent = joinInfo.nContent.replace(regex, '<span style="color: red !important; font-weight: bold !important;">$&</span>');
        var highlightedNTitle = joinInfo.nTitle.replace(regex, '<span style="color: red !important; font-weight: bold !important;">$&</span>');
        var highlightedNPeople = joinInfo.nPeople.replace(regex, '<span style="color: red !important; font-weight: bold !important;">$&</span>');
        listItem.innerHTML = `
        <span class="self-start font_4 text_13" id="nTitle">${"#" + highlightedNTitle + "#"}</span>
        <span class="self-start font_3 text_14 mt-15" id="nContent">${highlightedContent}</span>
        <div class="flex-row items-center self-stretch mt-15">
            <img class="image_4" src="../img/InFo/chuangjianren.png" />
            <span class="font text_15 ml-9" id="nPeople">${highlightedNPeople}</span>
            <span class="font text_15 ml-9" id="nTime">${joinInfo.createTime}</span>
        </div>
      `;

        announcementList.appendChild(listItem);
    });
}