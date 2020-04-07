$(function () {
    var del_id;
    $(".my-delete-btn").click(function () {
        //bootstrap 调起模态框，这里赋值id、、头晕、睡会
        del_id = $(this).children().attr("id");

    });
    $(".my-delete-verify").click(function () {
        $.ajax({
            type: "DELETE",//请求方式
            contentType: "application/json;charset=UTF-8",    //请求的媒体类型
            url: "/del/article/" + del_id,    //请求地址
            sync: true,//异步
            dataType: "json",
            success: function (data) {
                location.reload();
            },
            error: function (e) {
            }
        });
    })

});
