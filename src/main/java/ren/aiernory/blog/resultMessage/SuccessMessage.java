package ren.aiernory.blog.resultMessage;

import lombok.Data;
import ren.aiernory.blog.enums.SuccessCodeEnum;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.exception.ResultDto
 * @date 2020/3/24 11:39
 * @Description:
 */
@Data
public class SuccessMessage implements ResultMessage{
    private Integer code;
    private String message;
    
    @Override
    public String getMessage() {
        return message;
    }
    
    @Override
    public Integer getCode() {
        return code;
    }
    
    public SuccessMessage(SuccessCodeEnum successCode) {
        this.code = successCode.getCode();
        this.message = successCode.getMessage();
    }
    
    //public static SuccessMessage errorOf(Integer code, String message) {
    //    SuccessMessage successMessage = new SuccessMessage();
    //    successMessage.setCode(code);
    //    successMessage.setMessage(message);
    //    return successMessage;
    //}
}
