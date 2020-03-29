package ren.aiernory.blog.enums;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.enums.CommentTypeEnum
 * @date 2020/3/24 11:47
 * @Description:
 */
public enum CommentTypeEnum {
    
    ARTICLE(1),
    COMMENT(2);
    private Integer type;
    
    CommentTypeEnum(Integer type) {
        this.type = type;
    }
    
    public Integer getType() {
        return type;
    }
    
    public void setType(Integer type) {
        this.type = type;
    }
}
