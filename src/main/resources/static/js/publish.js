$(function () {

    //检测输入完整性，以开放发表按钮
    window.setInterval(function () {
        submitVerify();
    }, 2000);
    function submitVerify() {

        if ($("#publishTitle").val() != '' && $("#test-editormd").children("textarea[placeholder]").val() != '') {
            $("#publish-submit").removeAttr("disabled");
            $("#publish-submit").removeClass("btn-danger");
            $("#publish-submit").addClass("btn-success");
        } else {
            $("#publish-submit").attr("disabled", "true");
            $("#publish-submit").removeClass("btn-success");
            $("#publish-submit").addClass("btn-danger");
        }
    }


    //edit时，显示音乐信息
    let editMusicMsg = $("#edit-music-msg").html();
    if(editMusicMsg.length>0){
        let split = editMusicMsg.split("/*-");
        let type;
        let divMsg;
        if(split[0]=="1"){
            type="netease";
            divMsg="网易音乐id:"
        }else {
            type="local";
            divMsg="服务器本地音乐asd:"
        }
        $("#add-music-input").val("type="+type+"&musicId="+split[1]);
        $("#show-music-msg").html(divMsg+split[1]);
    }
    //清除音乐信息
    $("#clean-music-btn").click(function () {
        $("#show-music-msg").html("未选择音乐");
        $("#add-music-input").val("");
    });
    //文章内插入音乐信息
    $("#add-music-btn").click(function () {
        let label = $(".modal-body").children("ul").children(".active").children("a").html();
        let html;
        if (label == "网易音乐") {
            type = "netease";
            html = $("#netease-music-info").val();
            $("#show-music-msg").html("网易音乐id:"+html);
        } else if (label == "本地音乐") {
            type = "local";
            html = $(".localMusicButton.btn-info").html();
            $("#show-music-msg").html("服务器本地音乐:"+html);
        }
        $("#add-music-input").val("type=" + type + "&musicId=" + html);
    });



    //加载本地音乐
    $("#add-music-local-btn").click(function () {
        $.ajax({
            type: "GET",    //请求方式
            contentType: "application/json;charset=UTF-8",    //请求的媒体类型
            url: "/publish/music",    //请求地址
            data: "",    //参数，json字符串
            //成功
            success: function (result) {//result string数组
                showMusic(result);
            },
            //失败，包含具体的错误信息
            error: function (e) {
                console.log(e.status);
                console.log(e.responseText);
            }
        })
    });

    //本地音乐表格显示
    function showMusic(result) {
        console.log(result);
        $("#add-music-local-table").show();
        $("#add-music-local-table").empty();
        //按钮组button准备
        let btn = $(document.createElement("button"));
        btn.addClass("btn");//基本
        btn.attr("type", "button");//基本
        btn.addClass("btn-default");//基本样式
        btn.attr("style", "width: 90%; height: 50%; overflow: hidden; text-overflow:ellipsis; white-space: nowrap;");//大小等样式
        btn.addClass("localMusicButton");//识别类名
        btn.attr("data-toggle", "tooltip");//悬浮框
        btn.attr("data-placement", "right");//悬浮框位置
        // btn.attr("title","歌曲的全名显示");//显示内容

        //tr、td准备
        let tableRowElement = $(document.createElement("tr"));
        let cellElement = $(document.createElement("td"));
        //一行3个  j行数，i总计数
        for (let i = 0; i < result.length; i += 3) {//顺序着写，一轮完成3个
            let row = tableRowElement.clone();
            $("#add-music-local-table").append(row);
            let cell1 = cellElement.clone();
            let cell2 = cellElement.clone();
            let cell3 = cellElement.clone();
            let btn1 = btn.clone();
            btn1.html(result[i]);
            btn1.attr("title", result[i]);
            cell1.html(btn1);
            if (i + 2 < result.length) {//为了最后一行总得麻烦点
                let btn2 = btn.clone();
                btn2.html(result[i + 1]);
                btn2.attr("title", result[i + 1]);
                cell2.html(btn2);
                let btn3 = btn.clone();
                btn3.html(result[i + 2]);
                btn3.attr("title", result[i + 2]);
                cell3.html(btn3);
            } else if (i + 1 < result.length) {
                let btn2 = btn.clone();
                btn2.html(result[i + 1]);
                btn2.attr("title", result[i + 1]);
                cell2.html(btn2);
            }
            cell1.appendTo(row);
            cell2.appendTo(row);
            cell3.appendTo(row);
        }
        $("#add-music-local-btn").html("重新加载本地音乐");
        $("#add-music-local-btn").insertAfter($("#add-music-local-table"));
        //按钮响应
        musicButtonAction();
    }

    //本地音乐唯一选中（class = "localMusicButton btn-info"）
    function musicButtonAction() {
        $(".localMusicButton").click(function () {
            $(".localMusicButton").removeClass("btn-info");
            $(this).addClass("btn-info");
        })
    }

    //上传音乐
    $("#uploadMusic-btn").click(function () {
        $("#musicUploadMsg").attr("data-content", "正在上传...");
        let formData = new FormData();
        formData.append("music", $('#uploadMusic-file')[0].files[0]);
        $.ajax({
            type: 'POST',
            url: '/publish/music',
            dataType: 'text',
            async: false,
            data: formData,
            processData: false, // 使数据不做处理
            contentType: false, // 不要设置Content-Type请求头
            success: function (msg) {
                $("#musicUploadMsg").attr("data-content", msg);
            },
            error: function (msg) {
                $("#musicUploadMsg").attr("data-content", msg);
            }
        });
    });
    $('[data-toggle="popover"]').popover();




//种类选择树
    let sort_data =$("#sortJson").html();
    let parse = JSON.parse(sort_data);
    layui.use(['tree', 'util'], function () {
        var tree = layui.tree,
            layer = layui.layer,
            util = layui.util;
        //按钮事件
        util.event('lay-demo', {
            getChecked: function (othis) {
                let checkedData = tree.getChecked('checkbox-sort'); //获取选中节点的数据
                $("#sort-input").val(JSON.stringify(checkedData));
                let btnHtml = $("#sort-btn").html();
                if(btnHtml == '确定'){
                    $("#sort-btn").html("done，提交文章后生效");
                }else {
                    $("#sort-btn").html(btnHtml+"！");
                }
            }
        });
        tree.render({
            elem: '#publish-sort',//目标div
            data: parse,//数据源
            showCheckbox: true , //是否显示复选框
            accordion:false ,//手风琴模式.不能同时展开..
            id: 'checkbox-sort',//按钮操作需要id
            isJump :false,//跳转
            showLine:false,//连线
            //有一点，父节点不能单独选取，若果要改会很麻烦。尝试一下，不行就放弃。一个隐藏的来表示父节点
        });

    })

});

