var baseUrl="http://127.0.0.1:8089/api";

// 获取公告数据的页数和每页数量
const pageNum = 1; // 页数
const pageSize = 5; // 每页数量

// 构建带有查询参数的URL
const url = new URL(baseUrl+"/selectEmpinfo");
url.searchParams.append('pageNum', pageNum);
url.searchParams.append('pageSize', pageSize);

// 发送获取就业信息的请求
fetch(url, {
    method: 'GET',
    headers: {
        'Content-Type': 'application/json'
    }
})
    .then(response => {
        return response.json(); // 将响应数据转换为 JSON 格式
    })
    .then(data => {
        // 根据数据渲染公告列表
        const infoContainer = document.getElementById("infoContainer");// 获取存放就业信息列表的容器元素
        infoContainer.innerHTML = ""; // 清空原有内容
        data.rows.forEach(record => {
            const empId = record.empId;//empId
            const empName = record.empName; //empName
            const empPay = record.empPay; //empPay
            const empPayMax = record.empPayMax; //empPayMax
            const cName = record.cName; //cName
            const cAddress = record.cAddress; //cAddress
            const empDes = record.empDes; //empDes

            const div = document.createElement("div");
            div.className = "infomation_one";
            div.style.cssText = "display: flex;flex-direction: column;"

            // 职位与薪资
            const infomationXinxi = document.createElement("div");
            infomationXinxi.className = "infomation_xinxi";
            infomationXinxi.style.cssText = "width: 100%";

            const workPost = document.createElement("p");
            workPost.className = "work_post";
            workPost.style.cssText = "display: inline-block;float: left;margin-left: 2rem;font-size: 1.2rem;";
            const empNameSpan = document.createElement("div");
            empNameSpan.textContent = empName;
            workPost.appendChild(empNameSpan);
            infomationXinxi.appendChild(workPost);

            const h1 = document.createElement("h1");
            h1.style.cssText = "color: #51B9BD; padding-right: 1rem;padding-top: 0.4rem; display: inline-block; float: right; margin-right: 1rem;";
            const salarySpan = document.createElement("span");
            salarySpan.textContent = "月薪：";
            const empPaySpan = document.createElement("span");
            empPaySpan.id = "empPay";
            empPaySpan.textContent = empPay;
            const subSpan = document.createElement("span");
            subSpan.textContent = "-";
            const empPayMaxSpan = document.createElement("span");
            empPayMaxSpan.id = "empPayMax";
            empPayMaxSpan.textContent = empPayMax;
            const kSpan = document.createElement("span");
            kSpan.textContent = "K";
            h1.appendChild(salarySpan);
            h1.appendChild(empPaySpan);
            h1.appendChild(subSpan);
            h1.appendChild(empPayMaxSpan);
            h1.appendChild(kSpan);
            infomationXinxi.appendChild(h1);

            div.appendChild(infomationXinxi);

            //职位描述
            const description = document.createElement("div");
            description.className = "description";
            description.style.cssText = "width: 100%;";

            const empDesP = document.createElement("p");
            empDesP.style.cssText = "padding-left: 2.2rem;";
            const empDesSpans = empDes.split(/[，。]/);
            let length = 0;
            for (let j = 0; j < 5 && j < empDesSpans.length && length <= 12; j++) {
                const span = document.createElement("span");
                span.textContent = empDesSpans[j];
                length += empDesSpans[j].length;
                span.style.cssText = "font-size: 0.7rem; color: #616060; background-color: #f2f2f2; padding: 0.2em 0.5em; border-radius: 10px; margin-right: 5px;";
                empDesP.appendChild(span);
            }
            description.appendChild(empDesP);

            div.appendChild(description);

            const companyInfo = document.createElement("div");
            companyInfo.style.width = "100%";
            const cNameSpan = document.createElement("div");
            cNameSpan.style.cssText = "font-size: 0.7rem; display: inline-block; float: left; margin-left: 2.3rem;";
            cNameSpan.textContent = cName;
            const cAddressSpan = document.createElement("div");
            cAddressSpan.style.cssText = "font-size: 0.7rem; padding-right: 1rem; display: inline-block; float: right; margin-right: 1rem;";
            cAddressSpan.textContent = cAddress;
            companyInfo.appendChild(cNameSpan);
            companyInfo.appendChild(cAddressSpan);
            div.appendChild(companyInfo);

            div.addEventListener('click', () => {
                window.location.href = `../page/employment_Information.html?empId=${empId}`;
            });

            infoContainer.appendChild(div);
        });
    })
    .catch(error => {
        console.error('获取数据失败', error); // 捕获并打印获取数据失败的错误信息
    });

//实现搜索功能
function searchEmpDetails() {
    // 获取搜索框中的内容
    var searchText = document.getElementById('search-input').value;

    // 发送搜索请求给后端
    fetch(baseUrl+'/searchEmpDetails?keyword=' + searchText)
        .then(response => response.json())
        .then(data => {
            // 假设从后端获取到了 joinData 数据
            var joinData = data.data;
            renderempDetails(joinData); // 传入两个参数
        })
        .catch(error => {
            console.error('搜索失败:', error);
        });
}

// 渲染搜索到的内容
function renderempDetails(empDetails) {
    const infoContainer = document.getElementById("infoContainer");// 获取存放就业信息列表的容器元素
    infoContainer.innerHTML = ""; // 清空原有内容

    empDetails.forEach(record => {
        const empId = record.empId;//empId
        const empName = record.empName; //empName
        const empPay = record.empPay; //empPay
        const empPayMax = record.empPayMax; //empPayMax
        const cName = record.cName; //cName
        const cAddress = record.cAddress; //cAddress
        const empDes = record.empDes; //empDes

        const div = document.createElement("div");
        div.className = "infomation_one";
        div.style.cssText = "display: flex;flex-direction: column;"

        // 职位与薪资
        const infomationXinxi = document.createElement("div");
        infomationXinxi.className = "infomation_xinxi";
        infomationXinxi.style.cssText = "width: 100%";

        const workPost = document.createElement("p");
        workPost.className = "work_post";
        workPost.style.cssText = "display: inline-block;float: left;margin-left: 2rem;font-size: 1.5rem;";
        const empNameSpan = document.createElement("div");
        empNameSpan.textContent = empName;
        workPost.appendChild(empNameSpan);
        infomationXinxi.appendChild(workPost);

        const h1 = document.createElement("h1");
        h1.style.cssText = "color: #51B9BD; padding-right: 1rem;padding-top: 0.4rem; display: inline-block; float: right; margin-right: 1rem;";
        const salarySpan = document.createElement("span");
        salarySpan.textContent = "月薪：";
        const empPaySpan = document.createElement("span");
        empPaySpan.id = "empPay";
        empPaySpan.textContent = empPay;
        const subSpan = document.createElement("span");
        subSpan.textContent = "-";
        const empPayMaxSpan = document.createElement("span");
        empPayMaxSpan.id = "empPayMax";
        empPayMaxSpan.textContent = empPayMax;
        const kSpan = document.createElement("span");
        kSpan.textContent = "K";
        h1.appendChild(salarySpan);
        h1.appendChild(empPaySpan);
        h1.appendChild(subSpan);
        h1.appendChild(empPayMaxSpan);
        h1.appendChild(kSpan);
        infomationXinxi.appendChild(h1);

        div.appendChild(infomationXinxi);

        //职位描述
        const description = document.createElement("div");
        description.className = "description";
        description.style.cssText = "width: 100%;";

        const empDesP = document.createElement("p");
        empDesP.style.cssText = "padding-left: 2.2rem;";
        const empDesSpans = empDes.split(/[，。]/);
        let length = 0;
        for (let j = 0; j < 5 && j < empDesSpans.length && length <= 12; j++) {
            const span = document.createElement("span");
            span.textContent = empDesSpans[j];
            length += empDesSpans[j].length;
            span.style.cssText = "font-size: 0.7rem; color: #616060; background-color: #f2f2f2; padding: 0.2em 0.5em; border-radius: 10px; margin-right: 5px;";
            empDesP.appendChild(span);
        }
        description.appendChild(empDesP);

        div.appendChild(description);

        const companyInfo = document.createElement("div");
        companyInfo.style.width = "100%";
        const cNameSpan = document.createElement("div");
        cNameSpan.style.cssText = "font-size: 0.7rem; display: inline-block; float: left; margin-left: 2.3rem;";
        cNameSpan.textContent = cName;
        const cAddressSpan = document.createElement("div");
        cAddressSpan.style.cssText = "font-size: 0.7rem; padding-right: 1rem; display: inline-block; float: right; margin-right: 1rem;";
        cAddressSpan.textContent = cAddress;
        companyInfo.appendChild(cNameSpan);
        companyInfo.appendChild(cAddressSpan);
        div.appendChild(companyInfo);

        div.addEventListener('click', () => {
            window.location.href = `../page/employment_Information.html?empId=${empId}`;
        });

        infoContainer.appendChild(div);
    });



    if (empDetails.length === 0) {
        // 没有搜索到结果
        const noResultItem = document.createElement('div');
        noResultItem.classList.add('text-center', 'mt-20', 'font_2', 'text_13');
        noResultItem.textContent = '没有搜索到相关内容';
        infoContainer.appendChild(noResultItem);
    }

}
