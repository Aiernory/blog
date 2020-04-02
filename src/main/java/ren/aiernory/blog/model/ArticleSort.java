package ren.aiernory.blog.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.model.ArticleSort
 * @date 2020/4/1 19:54
 * @Description:
 */
@Data
@Table(name = "article_sort")
public class ArticleSort {
    
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    private Integer pId;
    private Integer sId;
    
}