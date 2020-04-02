package ren.aiernory.blog.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.model.Sort
 * @date 2020/4/1 18:54
 * @Description:
 */
@Data
@Table(name = "sort")
public class Sort {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    private String name;
    private Integer parent;
    @Transient
    private String href;
    @Transient
    List<Sort> children;
}
