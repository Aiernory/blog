package ren.aiernory.blog.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.model.Label
 * @date 2020/3/29 19:51
 * @Description:
 */
@Data
@Table(name = "label")
public class Label {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    private String name;
    private Integer count;
}
