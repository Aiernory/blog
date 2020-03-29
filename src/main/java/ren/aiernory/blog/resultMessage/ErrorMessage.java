package ren.aiernory.blog.resultMessage;

import ren.aiernory.blog.enums.ErrorCodeEnum;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.exception.CustomizeException
 * @date 2020/3/20 17:30
 * @Description:
 */

public class ErrorMessage extends RuntimeException implements ResultMessage {
    
    private String message;
    private Integer code;
    
    public ErrorMessage(ErrorCodeEnum errorCode) {
        this.message = errorCode.getMessage();
        this.code=errorCode.getCode();
    }
    
    @Override
    public String getMessage() {
        return message;
    }
    
    @Override
    public Integer getCode() {
        return code;
    }
}
