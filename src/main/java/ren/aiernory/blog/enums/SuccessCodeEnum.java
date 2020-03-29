package ren.aiernory.blog.enums;

import ren.aiernory.blog.resultMessage.ResultMessage;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.enums.SuccessCodeEnum
 * @date 2020/3/24 13:11
 * @Description:
 */
public enum  SuccessCodeEnum implements ResultMessage {
    QUESTION_NOT_FOUND(2001,"你找到文章不存在，请尽量不要手动输出文章路径。。（又或许文章被删除了"),
    QUESTION_UPDATE_FAILED(2002,"更新失败，...，就在前一刻，服务器炸了||文章被删除了"),
    NOT_LOGIN_PUBLISH(2003,"请先登录后在撰写文章"),
    NOT_LOGIN_COMMENT(2004,"请先登录后在评论");
    
    private Integer code;
    private String message;
    
    SuccessCodeEnum(Integer code, String message) {
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
