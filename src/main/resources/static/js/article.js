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
        //      btn     -span    -div    -mediaBody -media  -li
        let li=$(this).parent().parent().parent().parent();
        $("#comment-input-dialog").insertAfter(li);
        //show-input-comment-id
        let id = $(this).attr("id");
        let split = id.split("-");
        $("#comment-input-dialog").find("input[name=parent]").val(split[3]);
        $(".show-input-article").show();
    });
    //子评论移动编辑框
    $(".show-input-child-comment").click(function () {
        //      btn     -span    -div    -mediaBody -media-modalBody  -li
        let li=$(this).parent().parent().parent().parent().parent();
        $("#comment-input-dialog").insertAfter(li);
        //show-input-comment-id
        let id = $(this).attr("id");
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


    //未输入内容，评论按钮不可用
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

///////////////////////////////////////////////////////这里开始，是新加入、测试
//    标签功能调试完成。
//      遗留问题，未登录信息的返回....

    let label = $(".label-area-btn-add");

    //持久标签，可以查找
    let labelSearch = label.clone();
    labelSearch.removeClass("label-area-btn-add");
    labelSearch.addClass("label-area-btn-search");

    //不持久标签，点击触发controller..的likeLabel方法
    let labelNoSearch = label.clone();
    labelNoSearch.removeClass("label-area-btn-add");
    labelNoSearch.addClass("label-area-btn-noSearch");
    let labelRedHeart = $(".label-area-temp-heart").clone();
    labelRedHeart.show();
    labelRedHeart.appendTo(labelNoSearch);


    let articleId = $("#label-area-articleId").html();
    let data = {};
    data["articleId"] = articleId;
    let json = JSON.stringify(data);

    //永久标签加载
    $.ajax({
        type: "POST",//请求方式
        contentType: "application/json;charset=UTF-8",    //请求的媒体类型
        url: "/gLabel",    //请求地址
        data: json,    //参数，json字符串
        sync: true,//异步
        dataType:"json",
        //成功
        success: function (data) {
            for (let i = 0; i < data.length; i++) {
                let i_data = data[i];
                let i_btn = labelSearch.clone();
                i_btn.children(".label-area-btn-span").html(i_data);
                i_btn.insertAfter("#label-search-area");
            }
        },
        //失败，包含具体的错误信息
        error: function (e) {
        }
    });

    //临时标签加载
    $.ajax({
        type: "POST",//请求方式
        contentType: "application/json;charset=UTF-8",    //请求的媒体类型
        url: "/gTempLabel",    //请求地址
        data: json,    //参数，json字符串
        sync: true,//异步
        dataType:"json",
        //成功
        success: function (data) {
            $.each(data, function (key, value) {
                let i_btn = labelNoSearch.clone();
                i_btn.children(".label-area-btn-span").html(key);
                i_btn.children(".label-area-temp-heart").children().prop("id", "heart-" + key);
                i_btn.children(".label-area-temp-heart").children().html(value);
                i_btn.insertBefore(".label-area-btn-add");
            })
        },
        //失败，包含具体的错误信息
        error: function (e) {
        }
    });

    //添加标签          拷贝之前的data，不直接使用这个
    let label_area_btn_add = 0;//隐藏
    $(".label-area-btn-add").click(function () {
        if (label_area_btn_add == 0) {
            $("#label-area-input-add").show();
            $(".label-area-btn-add").children(".label-area-btn-span").html("隐藏输入框");
            label_area_btn_add = 1;
        } else {
            $("#label-area-input-add").val("");
            $("#label-area-input-add").hide();
            $(".label-area-btn-add").children(".label-area-btn-span").html("添加标签");
            label_area_btn_add = 0;
        }
    });
    $("#label-area-input-add").keydown(function (e) {
        if (e.keyCode == 13) {
            //提交labelName
            let labelName = $("#label-area-input-add").val();
            if (labelName.length > 1) {
                let add_data = data;
                add_data["labelName"] = labelName;
                let add_json = JSON.stringify(add_data);
                $.ajax({
                    type: "POST",
                    contentType: "application/json;charset=UTF-8",
                    url: "/label",
                    data: add_json,
                    sync: true,
                    success: function (result) {
                        if(result==""){
                            alert("数据重复")
                        }else {
                            //返回的是string。labelName.""空，重复了
                            let i_btn = labelNoSearch.clone();
                            i_btn.children(".label-area-btn-span").html(result);
                            i_btn.insertBefore(".label-area-btn-add");
                            i_btn.find(".glyphicon-heart").addClass("user-label-like");
                            i_btn.find(".glyphicon-heart").html(1);
                        }
                        //隐藏标签的输入框
                        $("#label-area-input-add").val("");
                        $("#label-area-input-add").hide();
                        $(".label-area-btn-add").children(".label-area-btn-span").html("添加标签");
                        label_area_btn_add = 0;
                    },
                    error: function (e) {
                    }
                });
            } else {
                alert("输入为空");
            }
        }
    });


    //glyphicon glyphicon-heart  增加class user-label-like

    //登录用户标签状态
    let session_user = $("#session-user-for-js").html();
    if (session_user.length < 1) {
        //隐藏添加标签按钮
        $(".label-area-btn-add").hide();
    } else {
        //登录状态ajax。。。当时候还有更麻烦的。根据登录否，超链接(查询)是否可用....单击响应(喜欢操作)是否可用....
        $.ajax({
            type: "POST",//请求方式
            contentType: "application/json;charset=UTF-8",
            url: "/gUserLabel",
            data: json,//只要一个文章id
            sync: true,//异步
            dataType:"json",
            success: function (data) {
                $.each(data, function (key, value) {
                    if (value == 1) {
                        $("#heart-" + key).addClass("user-label-like")
                    }
                })
            },
            error: function (e) {
            }
        });
    }

//点击like标签.因为动态添加，所以事件不能直接绑定。
    //解决1：绑定到上级标签--采用
    //解决2：提前制作copy、、
    $('#label-area').on("click", ".label-area-btn-noSearch", function () {
        let $this = $(this);
        let like_data = data;
        like_data["labelName"] = $this.children(".label-area-btn-span").html();
        let like_json = JSON.stringify(like_data);
        $.ajax({
            type: "PUT",
            contentType: "application/json;charset=UTF-8",
            url: "/label",
            data: like_json,
            sync: true,
            success: function (result) {
                if( typeof result =="object"){
                    //错误信息
                    console.log(result)
                }else {
                    //形如 1:3
                    let split = result.split(":");
                    if (split[0] == 1) {
                        //喜欢.不用管之前是什么，直接操作便是
                        $this.find(".glyphicon-heart").addClass("user-label-like")
                    } else {
                        //不喜欢
                        $this.find(".glyphicon-heart").removeClass("user-label-like")
                    }
                    $this.find(".glyphicon-heart").html(split[1]);
                }
            },
            error: function (e) {
                console.log(e)
            }
        });
    });


    //alert 需要改成自动消失的提示

});

