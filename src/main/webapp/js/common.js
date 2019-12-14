/*
        * <li class="nav-active"><a href="index.html">首页</a></li>
            <li><a href="route_list.html">门票</a></li>
            <li><a href="route_list.html">酒店</a></li>
            <li><a href="route_list.html">香港车票</a></li>
            <li><a href="route_list.html">出境游</a></li>
            <li><a href="route_list.html">国内游</a></li>
            <li><a href="route_list.html">港澳游</a></li>
            <li><a href="route_list.html">抱团定制</a></li>
            <li><a href="route_list.html">全球自由行</a></li>
            <li><a href="favoriterank.html">收藏排行榜</a></li>
        * */

function searchPara(str) {
    var arr = [];
    var s = str.substring(1);
    var arr1 = s.split("&");
    for (var i = 0;i < arr1.length;i++) {
        var split = arr1[i].split("=");
        arr.push(split[1]);
    }
    return arr;
}

function search() {
    var cid = searchPara(location.search)[0];
    load(cid, 1, $(".search_input").val());
}


// function load(cid, currentPage, condition) {
//     $.get("route/findAllById", {cid: cid, currentPage: currentPage, condition: condition}, function (data) {
//         $("#totalCount").text(data.totalCount);
//         $("#totalPage").text(data.totalPage);
//         //后一页
//         var afterNum = data.currentPage + 1;
//         if (afterNum > data.totalPage) {
//             afterNum = data.totalPage;
//         }
//         //前一页
//         var beforeNum = data.currentPage - 1;
//         if(beforeNum <= 0) {
//             beforeNum = 1;
//         }
//
//         console.log(afterNum);
//         console.log(beforeNum);
//         var arr = [];
//         arr.push('<li onclick="javascript:load('+cid+',1)"><a href="javascript:void(0)">首页</a></li>' +
//             '<li onclick = "load('+cid+','+beforeNum+')" class="threeword"><a href="#">上一页</a></li>');
//         var start = data.currentPage - 5;
//         var end = data.currentPage + 4;
//         if (start < 0) {
//             start = 1;
//             end = start + 9;
//         }
//         if (end > data.totalPage) {
//             end = data.totalPage;
//             start = end - 9;
//         }
//         for (var i = start; i<= end; i++) {
//             if (i == data.currentPage) {
//                 arr.push('<li class="curPage" onclick="javascript:load('+cid+','+i+')"><a href="javascript:void(0)">'+i+'</a></li>');
//             } else  {
//                 arr.push('<li onclick="javascript:load('+cid+','+i+')"><a href="javascript:void(0)">'+i+'</a></li>');
//             }
//         }
//         arr.push('<li onclick="javascript:load('+cid+','+afterNum+')" class="threeword"><a href="javascript:void(0);">下一页</a></li> ' +
//             '<li class="threeword" onclick="javascript:load('+cid+','+data.totalPage+')"><a href="javascript:void(0);">末页</a></li>');
//         $("#pages").html(arr.join(""));
//         /**
//          * <li>
//          <div class="img"><img src="images/04-search_03.jpg" alt=""></div>
//          <div class="text1">
//          <p>【减100元 含除夕/春节出发】广州增城三英温泉度假酒店/自由行套票</p>
//          <br/>
//          <p>1-2月出发，网付立享￥1099/2人起！爆款位置有限，抢完即止！</p>
//          </div>
//          <div class="price">
//          <p class="price_num">
//          <span>&yen;</span>
//          <span>299</span>
//          <span>起</span>
//          </p>
//          <p><a href="route_detail.html">查看详情</a></p>
//          </div>
//          </li>
//          */
//         var route = [];
//         console.log(data.list);
//         for (var i = 0;i < data.list.length;i++) {
//             var everyData = data.list[i];
//             route.push('<li>\n' +
//                 '                    <div " class="img"><img width="299" src="'+everyData.rimage+'" alt=""></div>\n' +
//                 '                        <div class="text1">\n' +
//                 '                        <p>'+everyData.rname+'</p>\n' +
//                 '                    <br/>\n' +
//                 '                    <p>'+everyData.routeIntroduce+'</p>\n' +
//                 '                    </div>\n' +
//                 '                    <div class="price">\n' +
//                 '                        <p class="price_num">\n' +
//                 '                        <span>&yen;</span>\n' +
//                 '                    <span>'+everyData.price+'</span>\n' +
//                 '                    <span>起</span>\n' +
//                 '                    </p>\n' +
//                 '                    <p><a href="route_detail.html">查看详情</a></p>\n' +
//                 '                    </div>\n' +
//                 '                    </li>');
//         }
//         $("#route").html(route.join(""));
//         window.scroll(0,0);
//     });
// }