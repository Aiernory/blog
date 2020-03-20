package ren.aiernory.blog.exception;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.exception.CustomizeErrorCode
 * @date 2020/3/20 17:46
 * @Description:
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode {
    
    QUESTION_NOT_FOUND("你找到文章不存在，请尽量不要手动输出文章路径。。（又或许文章被删除了")  ,
    QUESTION_UPDATE_FAILED("更新失败，...，就在前一刻，服务器炸了||文章被删除了")
    ;
    
    private String message;
    
    CustomizeErrorCode(String message) {
        this.message = message;
    }
    
    @Override
    public String getMessage() {
        return message;
    }
}
