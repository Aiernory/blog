$(function () {


    //检测输入完整性，以开放发表按钮
    window.setInterval(function () {
        submitVerify();
    }, 2000);
    // $("#publishTitle").bind('input propertychange', function () {
    //     alert("asd");
    //     submitVerify();
    // });
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

    //文章内插入音乐信息
    $("#add-music-btn").click(function () {
        let label = $(".modal-body").children("ul").children(".active").children("a").html();
        let html;
        if (label == "网易音乐") {
            type = "netease";
            html = $("#netease-music-info").val();
        } else if (label == "本地音乐") {
            type = "local";
            html = $(".localMusicButton.btn-info").html();
        }
        $("#add-music-input").val("type=" + type + "&musicId=" + html);
    });

    //加载本地音乐
    $("#add-music-local-btn").click(function () {
        $.ajax({
            type: "POST",    //请求方式
            contentType: "application/json;charset=UTF-8",    //请求的媒体类型
            url: "publish/localMusic",    //请求地址
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
        $("#add-music-local-btn").hide();
        $("#add-music-local-table").show();
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
            url: '/publish/uploadMusic',
            dataType: 'text',
            type: 'POST',
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
});

