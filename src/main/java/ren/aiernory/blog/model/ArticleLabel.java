package ren.aiernory.blog.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.model.ArticleLabel
 * @date 2020/3/30 11:32
 * @Description:
 */
@Data
@Table(name = "article_label")
public class ArticleLabel {
    
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    @Column(name = "p_id")
    private Integer articleId;
    @Column(name = "l_id")
    private Integer labelId;

}
