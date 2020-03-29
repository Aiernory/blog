$(function () {
    //分页页标的隐藏占位属性
    $(".hideden-placehode").attr("style","visibility:hidden");




    //排序导航条相关的
    let order = $("#list-order-input");
    $("#list-order-create").click(function () {
        if(order.val()==1){
            order.val(2);
        }else {
            order.val(1);
        }
        //submit
        $("#list-order-form").submit();
    });
    $("#list-order-modified").click(function () {
        if(order.val()==3){
            order.val(4);
        }else {
            order.val(3);
        }
        //submit
        $("#list-order-form").submit();
    });
    $("#list-order-count").click(function () {
        if(order.val()==5){
            order.val(6);
        }else {
            order.val(5);
        }
        //submit
        $("#list-order-form").submit();
    });

});