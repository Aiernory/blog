package ren.aiernory.blog.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.model.publish
 * @date 2020/3/4 1:09
 * @Description:
 */
@Data
@Table(name = "publish")
public class Publish {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    private String title;
    private String descriptionCode;
    private String descriptionDoc;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer viewCount;
    private Integer likeCount;
    private Integer commentCount;
    private Integer musicStatus;
    private String musicContent;
    @Transient
    private User author;
   
}
