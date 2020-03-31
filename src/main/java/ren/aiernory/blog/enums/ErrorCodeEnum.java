package ren.aiernory.blog.enums;

import ren.aiernory.blog.resultMessage.ResultMessage;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.exception.CustomizeErrorCode
 * @date 2020/3/20 17:46
 * @Description:
 */
public enum ErrorCodeEnum implements ResultMessage {
    REQUEST_ERROR(2000,"请求错误。"),
    QUESTION_NOT_FOUND(2001,"文章不存在、或已被删除"),
    QUESTION_UPDATE_FAILED(2002,"更新失败，...，就在前一刻，服务器炸了||文章被删除了"),
    NOT_LOGIN_PUBLISH(2003,"请先登录后在撰写文章"),
    NOT_LOGIN_COMMENT(2004,"请先登录后在评论"),
    NOT_FIND_COMMENT_PARENT(2005,"未选择评论的内容"),
    COMMENT_PARENT_NOT_FOUND(2006,"想要评论的内容找不到了"),
    QUESTION_UPDATE_NOT_AUTHOR(2007,"你不是作者，无权更新改文章！"),
    OPTION_NEED_LOGIN(2008,"该操作需要登陆")
    ;
    private Integer code;
    private String message;
    
    ErrorCodeEnum(Integer code, String message) {
        this.message = message;
        this.code = code;
    }
    
    @Override
    public Integer getCode() {
        return code;
    }
    
    @Override
    public String getMessage() {
        return message;
    }
}
