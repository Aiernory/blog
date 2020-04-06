$(function () {

    //layui
    //分类，单独的跳转
    $(".my-layui-nav-item").click(function () {
        location.href = $(this).attr("href");
    });
    //JavaScript代码区域
    layui.use('element', function () {
        var element = layui.element;
    });
    //end  layui


    //分页页标的隐藏占位属性
    $(".hideden-placehode").attr("style", "visibility:hidden");


    //排序导航条相关的
    let order = $("#list-order-input");
    $("#list-order-create").click(function () {
        if (order.val() == 1) {
            order.val(2);
        } else {
            order.val(1);
        }
        //submit
        $("#list-order-form").submit();
    });
    $("#list-order-modified").click(function () {
        if (order.val() == 3) {
            order.val(4);
        } else {
            order.val(3);
        }
        //submit
        $("#list-order-form").submit();
    });
    $("#list-order-count").click(function () {
        if (order.val() == 5) {
            order.val(6);
        } else {
            order.val(5);
        }
        //submit
        $("#list-order-form").submit();
    });


    //查询按钮
    //点按钮
    $("#header-search-btn").click(function () {
        search();
    });
    //按回车
    $("#header-search-input").keydown(function (keyword) {
        if (keyword.keyCode == 13) {
            search();
        }
    })
    function search() {
        let checked = $("#header-search-box").prop("checked")
        let search = $("#header-search-input").val();
        if(checked==true){
            location.href="/search/"+search;
        }else {
            location.href="/search/title/"+search;
        }
    }


});