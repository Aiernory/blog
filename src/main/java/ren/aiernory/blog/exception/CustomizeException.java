package ren.aiernory.blog.exception;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.exception.CustomizeException
 * @date 2020/3/20 17:30
 * @Description:
 */

public class CustomizeException extends RuntimeException {
    private String message;
    
    public CustomizeException(ICustomizeErrorCode customizeErrorCode) {
        this.message = customizeErrorCode.getMessage();
    }
    
    @Override
    public String getMessage() {
        return message;
    }
}
