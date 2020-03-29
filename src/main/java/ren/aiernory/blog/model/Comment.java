package ren.aiernory.blog.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.model.Comment
 * @date 2020/3/23 23:17
 * @Description:
 */
@Data
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    private String content;
    private Integer article;
    private Integer parent;
    private Integer creator;
    private Integer likeCount;
    private Integer commentCount;
    private Long gmtCreate;
    private Long gmtModified;

    @Transient
    private User author;
    @Transient
    private List<Comment> childComment;
}
