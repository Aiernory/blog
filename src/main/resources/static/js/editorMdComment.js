$(function () {
    $("#test-editormd").removeAttr("class");
    var md=$("#mdeditor-textarea").text();
    let toolBarLoadTimes=0;
    $("#test-editormd").html("");
    editormd("test-editormd", {
        autoFocus : false,
        location:"inherit",
        width: "100%",
        height: 300,
        path: '/plugins/editor.md-master/lib/',
        theme: "elegant",
        previewTheme: "elegant",
        editorTheme: "elegant",
        markdown: md,
        codeFold: true,
        saveHTMLToTextarea: true,    // 保存 HTML 到 Textarea
        searchReplace: true,
        htmlDecode: "style,script,iframe|on*",            // 开启 HTML 标签解析，为了安全性，默认不开启
        emoji: true,
        taskList: true,
        tocm: true,         // Using [TOCM]
        tex: true,                   // 开启科学公式TeX语言支持，默认关闭
        flowChart: true,             // 开启流程图支持，默认关闭
        sequenceDiagram: true,       // 开启时序/序列图支持，默认关闭,
        onload: function () {
            // this.fullscreen();
            this.unwatch();
            // this.fullscreen(false);
        },
        toolbarIcons : function() {
            // Or return editormd.toolbarModes[name]; // full, simple, mini
            // Using "||" set icons align right.
            var toolBarIconArray=editormd.toolbarModes["full"];

            if(toolBarLoadTimes>0){
                toolBarIconArray.push("emojiDownload");
            }else {
                toolBarIconArray.push("codeDownload");
                toolBarLoadTimes=toolBarLoadTimes+1;
            }

            return toolBarIconArray;
        },
        toolbarIconsClass : {
            codeDownload : "" , // 指定一个FontAawsome的图标类
            emojiDownload: "",
        },
        toolbarIconTexts : {
            codeDownload : "" , // 如果没有图标，则可以这样直接插入内容，可以是字符串或HTML标签
            emojiDownload : "",
        },
        // 自定义工具栏按钮的事件处理
        toolbarHandlers : {
            codeDownload : function() {
                this.executePlugin("codeDownloadDialog", "code-download-dialog/code-download-dialog");
            },
            emojiDownload : function() {
                this.executePlugin("emojiDownloadDialog", "emoji-download-dialog/emoji-download-dialog");
            },

        }
    });
});