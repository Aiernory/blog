$(function () {
    //分页页标的隐藏占位属性
    $(".hideden-placehode").attr("style", "visibility:hidden");


    //往后台上传评论，文章级别、父评论级别
    $("#submit-comment-editormd").click(function () {
        let form = $("#comment-editormd").serializeArray();
        let data = {};
        $.each(form, function () {
            data[this.name] = this.value;
        });
        let json = JSON.stringify(data);
        $.ajax({
            type: "POST",//请求方式
            contentType: "application/json;charset=UTF-8",    //请求的媒体类型
            url: "/comment",    //请求地址
            data: json,    //参数，json字符串
            sync: true,//异步
            //成功
            success: function (result) {
                // //动态跟新页面、输入框清空、自动消失的小提示评论成功
                // $("#comment-input-dialog").hide();
                // let json = JSON.parse(result);
                // let parent = json.parent;
                // if(parent==-1){
                //     comment1(json);
                // }else {
                //     comment2(json);
                // }
                location.reload();
            },
            //失败，包含具体的错误信息
            error: function (e) {
            }
        })
    });

    // //1级评论动态生成
    // function comment1(json){
    //
    // }
    // //2级评论动态生成
    // function comment2(json){
    //
    // }




    //父评论级别、移动文字编辑框
    $(".show-input-comment").click(function () {
        let li = $(this).parent().parent().parent().parent();
        $("#comment-input-dialog").insertAfter(li);
        let id = li.parent().parent().parent().attr("id");
        let split = id.split("-");
        $("#comment-input-dialog").find("input[name=parent]").val(split[2]);
        $(".show-input-article").show();
    });
    //子评论移动编辑框
    $(".show-input-child-comment").click(function () {
        let li = $(this).parent().parent().parent().parent();
        $("#comment-input-dialog").insertAfter(li);
        let id = li.parent().parent().attr("id");
        let split = id.split("-");
        $("#comment-input-dialog").find("input[name=parent]").val(split[3]);
        $(".show-input-article").show();
    });
    //从父评论返回文章评论duik=huakuang移动
    $(".show-input-article").click(function () {

        $("#comment-input-dialog").insertAfter($("#comment-list-div"));
        $("#comment-input-dialog").find("input[name=parent]").val(-1);
        $(".show-input-article").hide();
    });
    //返回文章评论初始隐藏
    $(".show-input-article").hide();


    //未输入，评论按钮不可用
    window.setInterval(function () {
        submitVerify();
    }, 2000);
    function submitVerify() {
        if ($("#test-editormd").children("textarea[placeholder]").val() == '') {
            $("#submit-comment-editormd").attr("disabled", "true");
            $("#submit-comment-editormd").addClass("btn-danger");
            $("#submit-comment-editormd").removeClass("btn-info");
        } else {
            $("#submit-comment-editormd").removeAttr("disabled");
            $("#submit-comment-editormd").addClass("btn-info");
            $("#submit-comment-editormd").removeClass("btn-danger");
        }
    }






});

