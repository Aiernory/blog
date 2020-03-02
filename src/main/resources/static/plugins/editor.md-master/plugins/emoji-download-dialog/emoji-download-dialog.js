/*!
 * 表情下载中（应该是）
 * @author      Aiernory
 */

(function () {

    var factory = function (exports) {

        var $ = jQuery;
        var pluginName = "emoji-download-dialog";

        exports.fn.emojiDownloadDialog = function () {
            var _this = this;
            var lang = this.lang;
            var editor = this.editor;
            var settings = this.settings;
            var path = settings.pluginPath + pluginName + "/";
            var classPrefix = this.classPrefix;
            var dialogName = classPrefix + pluginName, dialog;

            if (editor.find("." + dialogName).length < 1) {
                var dialogContent =
                    "<div class='editormd-form' style='padding: 10px 0;'> " + dialogLang.label + "</div>";

                dialog = this.createDialog({
                    name: dialogName,
                    title: "表情下载...（应该是）",
                    width: 400,
                    height: 180,
                    mask: settings.dialogShowMask,
                    drag: settings.dialogDraggable,
                    content: dialogContent,
                    lockScreen: settings.dialogLockScreen,
                    maskStyle: {
                        opacity: settings.dialogMaskOpacity,
                        backgroundColor: settings.dialogMaskBgColor
                    },
                    buttons: {
                        enter: [lang.buttons.enter, function () {
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
