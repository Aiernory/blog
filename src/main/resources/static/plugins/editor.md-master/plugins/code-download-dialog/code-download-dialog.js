/*!
 * md代码复制
 * @author      Aiernory
 */
(function () {

	var factory = function (exports) {

		var $ = jQuery;
		var pluginName = "code-download-dialog";

		exports.fn.codeDownloadDialog = function () {
			var _this = this;
			var lang = this.lang;
			var editor = this.editor;
			var settings = this.settings;
			var path = settings.pluginPath + pluginName + "/";
			var classPrefix = this.classPrefix;
			var dialogName = classPrefix + pluginName, dialog;

			if (editor.find("." + dialogName).length < 1) {
				var dialogContent = "<div class=\"markdown-body\" id='markdown-body-id' " +
					"style=\"font-family:微软雅黑, Helvetica, Tahoma, STXihei,Arial;height:390px;overflow:auto;font-size:14px;border-bottom:1px solid #ddd;padding:0 20px 20px 0;\">" +
					"</div>" +
					"<textarea id='hideTextAreaForCopyId'" +
					"style='position: absolute;top: 0;left: 0;opacity: 0;z-index: -10;'>" +
					"</textarea>";

				dialog = this.createDialog({
					name: dialogName,
					title: "code-Html源码----如果内容没有刷新，请先打开实时预览",
					width: 840,
					height: 540,
					mask: settings.dialogShowMask,
					drag: settings.dialogDraggable,
					content: dialogContent,
					lockScreen: settings.dialogLockScreen,
					maskStyle: {
						opacity: settings.dialogMaskOpacity,
						backgroundColor: settings.dialogMaskBgColor
					},
					buttons: {
						copy: ["一键复制", function () {
							//待复制的内容 hideTextAreaForCopyId
							var copyVal = document.getElementById("markdown-body-id").innerText;
							var hideTextAreaForCopyIdInput = document.getElementById("hideTextAreaForCopyId");
							hideTextAreaForCopyIdInput.value = copyVal;
							hideTextAreaForCopyIdInput.select(); // 选中文本
							try {
								if (document.execCommand('copy')) {
									//success info
									console.log("copy success...");
								} else {
									//fail info
									console.log("copy fail...");
								}
							} catch (err) {
								//fail info
								console.log("copy error...");
							} finally {
								return false;
							}
						}],
						close: [lang.buttons.close, function () {
							this.hide().lockScreen(false).hideMask();
							return false;
						}],
					}
				});
			}

			dialog = editor.find("." + dialogName);

			this.dialogShowMask(dialog);
			this.dialogLockScreen();
			dialog.show();

			var helpContent = dialog.find(".markdown-body");

			var htmlBody = $(".editormd-preview-container").eq(0).html();
			var htmlPrefix = "<!DOCTYPE html>\n" +
				"<html lang=\"zh\">\n" +
				"<head>\n" +
				"    <meta charset=\"utf-8\"/>\n" +
				"    <title>文章编辑</title>\n" +
				"</head>\n" +
				"<body>";
			var htmlSuffix = "</body>\n" +
				"</html>";
			helpContent.text(htmlPrefix + htmlBody + htmlSuffix);


			dialog.find("." + classPrefix + "dialog-close").bind(editormd.mouseOrTouch("click", "touchend"), function () {
				helpContent.html("");
				return this;
			});
		};

	};

	// CommonJS/Node.js
	if (typeof require === "function" && typeof exports === "object" && typeof module === "object") {
		module.exports = factory;
	} else if (typeof define === "function")  // AMD/CMD/Sea.js
	{
		if (define.amd) { // for Require.js

			define(["editormd"], function (editormd) {
				factory(editormd);
			});

		} else { // for Sea.js
			define(function (require) {
				var editormd = require("./../../editormd");
				factory(editormd);
			});
		}
	} else {
		factory(window.editormd);
	}

})();
