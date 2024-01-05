// 柱状图模块1
// import result from "../../../graduate-ui/src/components/Crontab/result";
(function() {
  // 1实例化对象
  var myChart = echarts.init(document.querySelector(".bar .chart"));
  // 2. 指定配置项和数据
  var option = {
    color: ["#2f89cf"],
    tooltip: {
      trigger: "axis",
      axisPointer: {
        // 坐标轴指示器，坐标轴触发有效
        type: "shadow" // 默认为直线，可选为：'line' | 'shadow'
      }
    },
    // 修改图表的大小
    grid: {
      left: "0%",
      top: "10px",
      right: "0%",
      bottom: "4%",
      containLabel: true
    },
    xAxis: [
      {
        type: "category",
        data: [],
        axisTick: {
          alignWithLabel: true
        },
        // 修改刻度标签 相关样式
        axisLabel: {
          color: "rgba(255,255,255,.6) ",
          fontSize: "12"
        },
        // 不显示x坐标轴的样式
        axisLine: {
          show: false
        }
      }
    ],
    yAxis: [
      {
        type: "value",
        // 修改刻度标签 相关样式
        axisLabel: {
          color: "rgba(255,255,255,.6) ",
          fontSize: 12
        },
        // y轴的线条改为了 2像素
        axisLine: {
          lineStyle: {
            color: "rgba(255,255,255,.1)",
            width: 2
          }
        },
        // y轴分割线的颜色
        splitLine: {
          lineStyle: {
            color: "rgba(255,255,255,.1)"
          }
        }
      }
    ],
    series: [
      {
        name: "就业人数",
        type: "bar",
        barWidth: "35%",
        data: [],
        itemStyle: {
          // 修改柱子圆角
          barBorderRadius: 5
        }
      }
    ]
  };
  // 3. 把配置项给实例对象
  myChart.setOption(option);
  // 4. 让图表跟随屏幕自动去适应
  window.addEventListener("resize", function() {
    myChart.resize();
  });

  //ajax请求
  // function(){}
  //当页面加载的时候发送ajax请求到后台
  $(function (){
    //定义新数组来存储后端发送过来的值
    var arrName = [];
    var arrCount = [];
    //url,data
    $.get('http://localhost:8089/api/graduation',function (data){
      console.log(data.data);
      //遍历后端发送过来的值
      for (var i = 0 ;i<data.data.length; i++){
        //将遍历出来的值存到新数组中
        arrName.push(data.data[i].name);
        arrCount.push(data.data[i].employment_count);
        //隐藏加载动画
        myChart.hideLoading();
        //覆盖上面data数据
        myChart.setOption({
          xAxis: [
            {
              type: "category",
              data: arrName,
              axisTick: {
                alignWithLabel: true
              },
              // 修改刻度标签 相关样式
              axisLabel: {
                interval:0,//将横轴信息全部显示出来
                color: "rgba(255,255,255,.6) ",
                fontSize: "12"
              },
              // 不显示x坐标轴的样式
              axisLine: {
                show: false
              }
            }
          ],
          series: [
            {
              name: "就业人数",
              type: "bar",
              barWidth: "35%",
              data: arrCount,
              itemStyle: {
                // 修改柱子圆角
                barBorderRadius: 5
              }
            }
          ]
        })
      }
    },"json");
  });
})();


(function() {
  var myColor = ["#1089E7", "#F57474", "#56D0E3", "#F8B448", "#8B78F6"];
  // 1. 实例化对象
  var myChart = echarts.init(document.querySelector(".bar2 .chart"));
  // 2. 指定配置和数据
  var option = {
    grid: {
      top: "10%",
      left: "22%",
      bottom: "10%"
      // containLabel: true
    },
    // 不显示x轴的相关信息
    xAxis: {
      show: false
    },
    yAxis: [
      {
        type: "category",
        inverse: true,
        data: [],
        // 不显示y轴的线
        axisLine: {
          show: false
        },
        // 不显示刻度
        axisTick: {
          show: false
        },
        // 把刻度标签里面的文字颜色设置为白色
        axisLabel: {
          color: "#fff"
        }
      },
      {
        data: [],
        inverse: true,
        // 不显示y轴的线
        axisLine: {
          show: false
        },
        // 不显示刻度
        axisTick: {
          show: false
        },
        // 把刻度标签里面的文字颜色设置为白色
        axisLabel: {
          color: "#fff"
        }
      }
    ],
    series: [
      {
        name: "条",
        type: "bar",
        data: [],
        yAxisIndex: 0,
        // 修改第一组柱子的圆角
        itemStyle: {
          barBorderRadius: 20,
          // 此时的color 可以修改柱子的颜色
          color: function(params) {
            // params 传进来的是柱子对象
            // console.log(params);
            // dataIndex 是当前柱子的索引号
            return myColor[params.dataIndex];
          }
        },
        // 柱子之间的距离
        barCategoryGap: 50,
        //柱子的宽度
        barWidth: 10,
        // 显示柱子内的文字
        label: {
          show: true,
          position: "inside",
          // {c} 会自动的解析为 数据  data里面的数据
          formatter: "{c}%"
        }
      },
      {
        name: "框",
        type: "bar",
        barCategoryGap: 50,
        barWidth: 15,
        yAxisIndex: 1,
        data: [100, 100, 100, 100],
        itemStyle: {
          color: "none",
          borderColor: "#00c1de",
          borderWidth: 3,
          barBorderRadius: 15
        }
      }
    ]
  };

  // 3. 把配置给实例对象
  myChart.setOption(option);
  // 4. 让图表跟随屏幕自动的去适应
  window.addEventListener("resize", function() {
    myChart.resize();
  });

  //ajax请求
  $(function (){
    //定义新数组来存储后端发送过来的值
    var arrType=[];
    var arrCount=[];
    var arrPercentage=[];
    $.get("http://127.0.0.1:8089/api/getCountCktpye",function (maps){
      //遍历后端发送过来的值
     for (var i=0;i<maps.data.length;i++){
       var type=maps.data[i].name
       if (type==0){
         type="私企"
       }else if (type==1){
         type="央企"
       }else if (type==2){
         type="外企"
       }else if (type==3){
         type="国企"
       }
        //将遍历出来的值存到数组中
       arrType.push(type);
       arrCount.push(maps.data[i].count);
       arrPercentage.push(maps.data[i].percentage);

       //隐藏加载动画
       myChart.hideLoading();
       //覆盖上面的data数据
       myChart.setOption({
         yAxis: [
           {
             type: "category",
             inverse: true,
             data: arrType,
             // 不显示y轴的线
             axisLine: {
               show: false
             },
             // 不显示刻度
             axisTick: {
               show: false
             },
             // 把刻度标签里面的文字颜色设置为白色
             axisLabel: {
               color: "#fff"
             }
           },
           {
             data: arrCount,
             inverse: true,
             // 不显示y轴的线
             axisLine: {
               show: false
             },
             // 不显示刻度
             axisTick: {
               show: false
             },
             // 把刻度标签里面的文字颜色设置为白色
             axisLabel: {
               color: "#fff"
             }
           }
         ],
         series: [
           {
             name: "条",
             type: "bar",
             data: arrPercentage,
             yAxisIndex: 0,
             // 修改第一组柱子的圆角
             itemStyle: {
               barBorderRadius: 20,
               // 此时的color 可以修改柱子的颜色
               color: function(params) {
                 // params 传进来的是柱子对象
                 // console.log(params);
                 // dataIndex 是当前柱子的索引号
                 return myColor[params.dataIndex];
               }
             },
             // 柱子之间的距离
             barCategoryGap: 50,
             //柱子的宽度
             barWidth: 10,
             // 显示柱子内的文字
             label: {
               show: true,
               position: "inside",
               // {c} 会自动的解析为 数据  data里面的数据
               formatter: "{c}%"
             }
           },
           {
             name: "框",
             type: "bar",
             barCategoryGap: 50,
             barWidth: 15,
             yAxisIndex: 1,
             data: [100, 100, 100, 100],
             itemStyle: {
               color: "none",
               borderColor: "#00c1de",
               borderWidth: 3,
               barBorderRadius: 15
             }
           }
         ]
       })
      }
    },"json");
  });

})();


// 折线图定制

// 折线图1模块制作
(function() {
  // 1. 基于准备好的dom，初始化echarts实例
  var myChart = echarts.init(document.querySelector(".line .chart"));
  // 2. 指定配置和数据
  var option = {
    color: ["#00f2f1", "#ed3f35"],
    tooltip: {
      // 通过坐标轴来触发
      trigger: "axis"
    },
    legend: {
      // 距离容器10%
      right: "10%",
      // 修饰图例文字的颜色
      textStyle: {
        color: "#4c9bfd"
      }
      // 如果series 里面设置了name，此时图例组件的data可以省略
      // data: ["邮件营销", "联盟广告"]
    },
    grid: {
      top: "20%",
      left: "3%",
      right: "4%",
      bottom: "3%",
      show: true,
      borderColor: "#012f4a",
      containLabel: true
    },

    xAxis: {
      type: "category",
      boundaryGap: false,
      data: [
        "1月",
        "2月",
        "3月",
        "4月",
        "5月",
        "6月",
        "7月",
        "8月",
        "9月",
        "10月",
        "11月",
        "12月"
      ],
      // 去除刻度
      axisTick: {
        show: false
      },
      // 修饰刻度标签的颜色
      axisLabel: {
        color: "rgba(255,255,255,.7)"
      },
      // 去除x坐标轴的颜色
      axisLine: {
        show: false
      }
    },
    yAxis: {
      type: "value",
      // 去除刻度
      axisTick: {
        show: false
      },
      // 修饰刻度标签的颜色
      axisLabel: {
        color: "rgba(255,255,255,.7)"
      },
      // 修改y轴分割线的颜色
      splitLine: {
        lineStyle: {
          color: "#012f4a"
        }
      }
    },
    series: [
      {
        name: '已就业生人数',
        type: 'line',
        stack: 'Total',
        data: []
      }
    ]
  };



  // 3. 把配置给实例对象
  myChart.setOption(option);
  // 4. 让图表跟随屏幕自动的去适应
  window.addEventListener("resize", function() {
    myChart.resize();
  });

  // 5.点击切换效果
  $(".line h2").on("click", "a", function() {
    // alert(1);
    // console.log($(this).index());
    // 点击 a 之后 根据当前a的索引号 找到对应的 yearData的相关对象
    // console.log(yearData[$(this).index()]);
    var obj = yearData[$(this).index()];
    option.series[0].data = obj.data[0];
    option.series[1].data = obj.data[1];
    // 需要重新渲染
    myChart.setOption(option);
  });

//页面加载的时候发送请求1
  $(function (){
    var arrMonth = [];
    var arrCount = [];
    // 发送AJAX异步请求去Servlet后台获取用户数量的数据
    $.get("http://localhost:8089/api/Personnel",function (pes){
      for (var i = 0; i<pes.pes.length;i++){
        arrMonth.push(pes.pes[i].month);
        arrCount.push(pes.pes[i].count);
        myChart.hideLoading(); //隐藏加载动画
        myChart.setOption({
          xAxis: {
            type: "category",
            boundaryGap: false,
            data: arrMonth,
            axisTick: {
              show: false // 去除刻度线
            },
            axisLabel: {
              interval:0,//将横轴信息全部显示出来
              color: "#4c9bfd" // 文本颜色
            },
            axisLine: {
              show: false // 去除轴线
            }
          },
          series: [
            {
              name: '已就业生人数',
              type: 'line',
              stack: 'Total',
              data: arrCount
            }
          ]
        });
      }
    },"json")
  });

})();



// 折线图 学生日签到数量 模块制作
$(function() {
  var myChart = echarts.init(document.querySelector(".line2 .chart"));
  // 发送GET请求获取数据
  $.get("http://127.0.0.1:8089/api/checkins", function(data) {
    // 处理返回的数据
    var xAxisData = [];
    var seriesData = [];
    var currentDate = new Date(); // 当前日期
    var oneMonthAgo = new Date();
    oneMonthAgo.setMonth(currentDate.getMonth() - 1); // 获取一个月前的日期

    for (var i = 0; i < data.data.length; i++) {
      var dateParts = data.data[i].date.split("-");
      var dataDate = new Date(dateParts[0], dateParts[1] - 1, dateParts[2]); // 构建日期对象

      // 只取一个月内的数据
      if (dataDate >= oneMonthAgo && dataDate <= currentDate) {
        var day = dateParts[2];
        xAxisData.push(day);
        seriesData.push(data.data[i].count);
      }
    }

    // 配置Echarts图表
    var option = {
      tooltip: {
        trigger: "axis"
      },
      legend: {
        top: "0%",
        data: [],
        textStyle: {
          color: "rgba(255,255,255,.5)",
          fontSize: "12"
        }
      },
      grid: {
        left: "10",
        top: "30",
        right: "10",
        bottom: "10",
        containLabel: true
      },
      xAxis: [
        {
          type: "category",
          boundaryGap: false,
          data: xAxisData,
          axisLabel: {
            textStyle: {
              color: "rgba(255,255,255,.6)",
              fontSize: 12
            }
          },
          axisLine: {
            lineStyle: {
              color: "rgba(255,255,255,.2)"
            }
          }
        }
      ],
      yAxis: [
        {
          type: "value",
          axisTick: { show: false },
          axisLine: {
            lineStyle: {
              color: "rgba(255,255,255,.1)"
            }
          },
          axisLabel: {
            textStyle: {
              color: "rgba(255,255,255,.6)",
              fontSize: 12
            }
          },
          splitLine: {
            lineStyle: {
              color: "rgba(255,255,255,.1)"
            }
          }
        }
      ],
      series: [
        {
          name: "日签到数量",
          type: "line",
          smooth: true,
          lineStyle: {
            color: "#0184d5",
            width: "2"
          },
          areaStyle: {
            color: new echarts.graphic.LinearGradient(
                0,
                0,
                0,
                1,
                [
                  {
                    offset: 0,
                    color: "rgba(1, 132, 213, 0.4)"
                  },
                  {
                    offset: 0.8,
                    color: "rgba(1, 132, 213, 0.1)"
                  }
                ],
                false
            ),
            shadowColor: "rgba(0, 0, 0, 0.1)"
          },
          symbol: "circle",
          symbolSize: 8,
          showSymbol: false,
          itemStyle: {
            color: "#0184d5",
            borderColor: "rgba(221, 220, 107, .1)",
            borderWidth: 12
          },
          data: seriesData
        }
      ]
    };
    // 渲染Echarts图表
    myChart.setOption(option);
  }, "json");
  window.addEventListener("resize", function() {
    myChart.resize();
  });
});




// 饼形图1
(function() {
  // 1. 实例化对象
  var myChart = echarts.init(document.querySelector(".pie .chart"));
  // 2.指定配置
  var option = {
    color: ["#065aab", "#066eab", "#0682ab", "#0696ab", "#06a0ab"],
    tooltip: {
      trigger: "item",
      formatter: "{a} <br/>{b}: {c} ({d}%)"
    },

    legend: {
      bottom: "0%",
      // 修改小图标的大小
      itemWidth: 10,
      itemHeight: 10,
      // 修改图例组件的文字为 12px
      textStyle: {
        color: "rgba(255,255,255,.5)",
        fontSize: "12"
      }
    },
    series: [
      {
        name: "就业人数",
        type: "pie",
        // 这个radius可以修改饼形图的大小
        // radius 第一个值是内圆的半径 第二个值是外圆的半径
        radius: ["40%", "60%"],
        center: ["50%", "45%"],
        avoidLabelOverlap: false,
        // 图形上的文字
        label: {
          show: false,
          position: "center"
        },
        // 链接文字和图形的线是否显示
        labelLine: {
          show: false
        },
        data: [
          { value: 1, name: "0岁以下" },
          { value: 4, name: "20-29岁" },
          { value: 2, name: "30-39岁" },
          { value: 2, name: "40-49岁" },
          { value: 1, name: "50岁以上" }
        ]
      }
    ]
  };
  //当页面加载的时候发送ajax请求到后台
  $(function (){
    //定义新数组来存储后端发送过来的值
    var arrName = [];
    var arrCount = [];
    //url,data
    $.get('http://127.0.0.1:8089/api/getCountStu',function (maps){
      //遍历后端发送过来的值
      for (var i = 0 ;i<maps.data.length; i++){
        //将遍历出来的值存到新数组中
        arrName.push(maps.data[i].tchr_name);
        arrCount.push(maps.data[i].student_count);
        //隐藏加载动画
        myChart.hideLoading();
        //覆盖上面data数据
        myChart.setOption({
          series: [
            {
              name: "就业人数",
              type: "pie",
              // 这个radius可以修改饼形图的大小
              // radius 第一个值是内圆的半径 第二个值是外圆的半径
              radius: ["40%", "60%"],
              center: ["50%", "45%"],
              avoidLabelOverlap: false,
              // 图形上的文字
              label: {
                show: false,
                position: "center"
              },
              // 链接文字和图形的线是否显示
              labelLine: {
                show: false
              },
              data: [
                { value: arrCount[0], name: arrName[0] },
                { value: arrCount[1], name: arrName[1] },
                { value: arrCount[2], name: arrName[2] },
                { value: arrCount[3], name: arrName[3] },
                { value: arrCount[4], name: arrName[4]}
              ]
            }
          ]

        })
      }
    },"json");
  });




  // 3. 把配置给实例对象
  myChart.setOption(option);
  // 4. 让图表跟随屏幕自动的去适应
  window.addEventListener("resize", function() {
    myChart.resize();
  });
})();



// 企业地区分布 地区分布模块
(function() {
  var myChart = echarts.init(document.querySelector(".pie2 .chart"));
  var option = {
    color: [
      "#006cff",
      "#60cda0",
      "#ed8884",
      "#ff9f7f",
      "#0096ff",
      "#9fe6b8",
      "#32c5e9",
      "#1d9dff"
    ],
    tooltip: {
      trigger: "item",
      formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {
      bottom: "0%",
      itemWidth: 10,
      itemHeight: 10,
      textStyle: {
        color: "rgba(255,255,255,.5)",
        fontSize: "12"
      }
    },
    series: [
      {
        name: "地区分布",
        type: "pie",
        radius: ["10%", "70%"],
        center: ["50%", "50%"],
        roseType: "radius",
        // 图形的文字标签
        label: {
          fontSize: 10
        },
        // 链接图形和文字的线条
        labelLine: {
          // length 链接图形的线条
          length: 6,
          // length2 链接文字的线条
          length2: 8
        },
        data: [
          // { value: 20, name: "上海" },
          // { value: 26, name: "北京" },
          // { value: 24, name: "山东" },
          // { value: 25, name: "河北" },
          // { value: 20, name: "江苏" },
          // { value: 25, name: "浙江" },
          // { value: 30, name: "湖北" },
          // { value: 42, name: "四川" }
        ]
      }
    ]
  };
  // ajax请求数据
  // ajax请求数据
  $(function () {
    var Count = [];
    $.get("http://127.0.0.1:8089/api/address", function(result) {
      // 获取返回的数据放入Count数组
      result.data.forEach(function(item) {
        Count.push({ value: item.count, name: item.province });
      });
      myChart.hideLoading(); // 隐藏加载动画
      myChart.setOption({
        series: [
          {
            name: "点位统计",
            type: "pie",
            // 如果radius是百分比则必须加引号
            radius: ["10%", "70%"],
            center: ["50%", "42%"],
            roseType: "radius",
            data: Count,
            // 修饰饼形图文字相关的样式 label对象
            label: {
              fontSize: 10
            },
            // 修饰引导线样式
            labelLine: {
              // 连接到图形的线长度
              length: 10,
              // 连接到文字的线长度
              length2: 10
            }
          }
        ]
      });
    }, "json");
  });

  myChart.setOption(option);
  // 监听浏览器缩放，图表对象调用缩放resize函数
  window.addEventListener("resize", function() {
    myChart.resize();
  });
})();






// 模拟飞行路线模块地图模块
(function() {
  var myChart = echarts.init(document.querySelector(".map .chart"));
  var geoCoordMap = {
    上海: [121.4648, 31.2891],
    东莞: [113.8953, 22.901],
    东营: [118.7073, 37.5513],
    中山: [113.4229, 22.478],
    临汾: [111.4783, 36.1615],
    临沂: [118.3118, 35.2936],
    丹东: [124.541, 40.4242],
    丽水: [119.5642, 28.1854],
    乌鲁木齐: [87.9236, 43.5883],
    佛山: [112.8955, 23.1097],
    保定: [115.0488, 39.0948],
    兰州: [103.5901, 36.3043],
    包头: [110.3467, 41.4899],
    北京: [116.4551, 40.2539],
    北海: [109.314, 21.6211],
    南京: [118.8062, 31.9208],
    南宁: [108.479, 23.1152],
    南昌: [116.0046, 28.6633],
    南通: [121.1023, 32.1625],
    厦门: [118.1689, 24.6478],
    台州: [121.1353, 28.6688],
    合肥: [117.29, 32.0581],
    呼和浩特: [111.4124, 40.4901],
    咸阳: [108.4131, 34.8706],
    哈尔滨: [127.9688, 45.368],
    唐山: [118.4766, 39.6826],
    嘉兴: [120.9155, 30.6354],
    大同: [113.7854, 39.8035],
    大连: [122.2229, 39.4409],
    天津: [117.4219, 39.4189],
    太原: [112.3352, 37.9413],
    威海: [121.9482, 37.1393],
    宁波: [121.5967, 29.6466],
    宝鸡: [107.1826, 34.3433],
    宿迁: [118.5535, 33.7775],
    常州: [119.4543, 31.5582],
    广州: [113.5107, 23.2196],
    廊坊: [116.521, 39.0509],
    延安: [109.1052, 36.4252],
    张家口: [115.1477, 40.8527],
    徐州: [117.5208, 34.3268],
    德州: [116.6858, 37.2107],
    惠州: [114.6204, 23.1647],
    成都: [103.9526, 30.7617],
    扬州: [119.4653, 32.8162],
    承德: [117.5757, 41.4075],
    拉萨: [91.1865, 30.1465],
    无锡: [120.3442, 31.5527],
    日照: [119.2786, 35.5023],
    昆明: [102.9199, 25.4663],
    杭州: [119.5313, 29.8773],
    枣庄: [117.323, 34.8926],
    柳州: [109.3799, 24.9774],
    株洲: [113.5327, 27.0319],
    武汉: [114.3896, 30.6628],
    汕头: [117.1692, 23.3405],
    江门: [112.6318, 22.1484],
    沈阳: [123.1238, 42.1216],
    沧州: [116.8286, 38.2104],
    河源: [114.917, 23.9722],
    泉州: [118.3228, 25.1147],
    泰安: [117.0264, 36.0516],
    泰州: [120.0586, 32.5525],
    济南: [117.1582, 36.8701],
    济宁: [116.8286, 35.3375],
    海口: [110.3893, 19.8516],
    淄博: [118.0371, 36.6064],
    淮安: [118.927, 33.4039],
    深圳: [114.5435, 22.5439],
    清远: [112.9175, 24.3292],
    温州: [120.498, 27.8119],
    渭南: [109.7864, 35.0299],
    湖州: [119.8608, 30.7782],
    湘潭: [112.5439, 27.7075],
    滨州: [117.8174, 37.4963],
    潍坊: [119.0918, 36.524],
    烟台: [120.7397, 37.5128],
    玉溪: [101.9312, 23.8898],
    珠海: [113.7305, 22.1155],
    盐城: [120.2234, 33.5577],
    盘锦: [121.9482, 41.0449],
    石家庄: [114.4995, 38.1006],
    福州: [119.4543, 25.9222],
    秦皇岛: [119.2126, 40.0232],
    绍兴: [120.564, 29.7565],
    聊城: [115.9167, 36.4032],
    肇庆: [112.1265, 23.5822],
    舟山: [122.2559, 30.2234],
    苏州: [120.6519, 31.3989],
    莱芜: [117.6526, 36.2714],
    菏泽: [115.6201, 35.2057],
    营口: [122.4316, 40.4297],
    葫芦岛: [120.1575, 40.578],
    衡水: [115.8838, 37.7161],
    衢州: [118.6853, 28.8666],
    西宁: [101.4038, 36.8207],
    西安: [109.1162, 34.2004],
    贵阳: [106.6992, 26.7682],
    连云港: [119.1248, 34.552],
    邢台: [114.8071, 37.2821],
    邯郸: [114.4775, 36.535],
    郑州: [113.4668, 34.6234],
    鄂尔多斯: [108.9734, 39.2487],
    重庆: [107.7539, 30.1904],
    金华: [120.0037, 29.1028],
    铜川: [109.0393, 35.1947],
    银川: [106.3586, 38.1775],
    镇江: [119.4763, 31.9702],
    长春: [125.8154, 44.2584],
    长沙: [113.0823, 28.2568],
    长治: [112.8625, 36.4746],
    阳泉: [113.4778, 38.0951],
    青岛: [120.4651, 36.3373],
    韶关: [113.7964, 24.7028],
    泸州: [105.4078, 28.9124],
    内江: [105.0645, 29.5858],
    宜宾: [105.0645, 29.5858],
    凉山: [102.2735, 27.8877],
    西昌: [102.27148,27.90058],
    乐山:[103.77254,29.55794 ]

  };

  var planePath =
    "path://M1705.06,1318.313v-89.254l-319.9-221.799l0.073-208.063c0.521-84.662-26.629-121.796-63.961-121.491c-37.332-0.305-64.482,36.829-63.961,121.491l0.073,208.063l-319.9,221.799v89.254l330.343-157.288l12.238,241.308l-134.449,92.931l0.531,42.034l175.125-42.917l175.125,42.917l0.531-42.034l-134.449-92.931l12.238-241.308L1705.06,1318.313z";
  //var planePath = 'arrow';
  var convertData = function(data) {
    var res = [];
    for (var i = 0; i < data.length; i++) {
      var dataItem = data[i];

      var fromCoord = geoCoordMap[dataItem[0].name];
      var toCoord = geoCoordMap[dataItem[1].name];
      if (fromCoord && toCoord) {
        res.push({
          fromName: dataItem[0].name,
          toName: dataItem[1].name,
          coords: [fromCoord, toCoord],
          value: dataItem[1].value
        });
      }
    }
    return res;
  };

  var color = ["#a6c84c", "#ffa022", "#46bee9"]; //航线的颜色
  var series = [];


  $.get("http://localhost:8089/api/graduatesNumber",function (res){

    $("#totalStu").prepend(res.other.sTotal);
    $("#graduationStu").prepend(res.other.gTotal);
    var com=res.other.company;
    var LDData = [];
    for(var i=0;i<com.length;i++){
      var arr= [{ name: "泸州" }, { name: com[i].cAddress, value: 1 }];
       LDData.push(arr);
    }
  [
    ["泸州", LDData],

  ].forEach(function(item, i) {
    series.push(
      {
        name: item[0] + " Top3",
        type: "lines",
        zlevel: 1,
        effect: {
          show: true,
          period: 6,
          trailLength: 0.7,
          color: "red", //arrow箭头的颜色
          symbolSize: 3
        },
        lineStyle: {
          normal: {
            color: color[i],
            width: 0,
            curveness: 0.2
          }
        },
        data: convertData(item[1])
      },
      {
        name: item[0] + " Top3",
        type: "lines",
        zlevel: 2,
        symbol: ["none", "arrow"],
        symbolSize: 10,
        effect: {
          show: true,
          period: 6,
          trailLength: 0,
          symbol: planePath,
          symbolSize: 15
        },
        lineStyle: {
          normal: {
            color: color[i],
            width: 1,
            opacity: 0.6,
            curveness: 0.2
          }
        },
        data: convertData(item[1])
      },
      {
        name: item[0] + " Top3",
        type: "effectScatter",
        coordinateSystem: "geo",
        zlevel: 2,
        rippleEffect: {
          brushType: "stroke"
        },
        label: {
          normal: {
            show: true,
            position: "right",
            formatter: "{b}"
          }
        },
        symbolSize: function(val) {
          return val[2] / 8;
        },
        itemStyle: {
          normal: {
            color: color[i]
          },
          emphasis: {
            areaColor: "#2B91B7"
          }
        },
        data: item[1].map(function(dataItem) {
          return {
            name: dataItem[1].name,
            value: geoCoordMap[dataItem[1].name].concat([dataItem[1].value])
          };
        })
      }
    );
  });

  var option = {
    tooltip: {
      trigger: "item",
      formatter: function(params, ticket, callback) {
        if (params.seriesType == "effectScatter") {
          return "线路：" + params.data.name + "" + params.data.value[2];
        } else if (params.seriesType == "lines") {
          return (
            params.data.fromName +
            ">" +
            params.data.toName +
            "<br />" +
            params.data.value
          );
        } else {
          return params.name;
        }
      }
    },
    legend: {
      orient: "vertical",
      top: "bottom",
      left: "right",
      data: ["重庆 Top3", "广州 Top3", "重庆 Top3"],
      textStyle: {
        color: "#fff"
      },
      selectedMode: "multiple"
    },
    geo: {
      map: "china",
      label: {
        emphasis: {
          show: true,
          color: "#fff"
        }
      },
      // 把中国地图放大了1.2倍
      zoom: 1.2,
      roam: true,
      itemStyle: {
        normal: {
          // 地图省份的背景颜色
          areaColor: "rgba(20, 41, 87,0.6)",
          borderColor: "#195BB9",
          borderWidth: 1
        },
        emphasis: {
          areaColor: "#2B91B7"
        }
      }
    },
    series: series
  };
  myChart.setOption(option);
  // 监听浏览器缩放，图表对象调用缩放resize函数
  window.addEventListener("resize", function() {
    myChart.resize();
  });
  })
})();
