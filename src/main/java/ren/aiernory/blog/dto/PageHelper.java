package ren.aiernory.blog.dto;

import lombok.Data;
import ren.aiernory.blog.model.Publish;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.dto.PageHelper
 * @date 2020/3/11 23:30
 * @Description:
 */
@Data
public class PageHelper {
    //分页展示内容，包含当前页的内容，和页标信息
    
    
    private Boolean showPrevious;
    private Boolean showFirstPage;
    private Boolean showNext;
    private Boolean showEndPage;
    
    private Integer maxPage;
    private Integer size;
    private Integer currentPage;
    
    
    private Integer order;
    private List<Publish> publishes;
    private List<Integer> pageList;
    
    
    public void setPageList(int totalCount) {
        
        maxPage = (int) Math.ceil((double) totalCount / size);
        int begin = 1;
        
        showPrevious = !currentPage.equals(1);
        showNext = !currentPage.equals(maxPage);
        
        pageList = new ArrayList<>();
        if (maxPage <= 5) {
            begin = 1;
        } else if (currentPage >= 3 && currentPage <= maxPage - 2) {
            begin = currentPage - 2;
        } else {
            if (currentPage <= 2) {
                begin = 1;
            } else if (currentPage >= maxPage - 2) {
                begin = maxPage - 4;
            }
        }
        
        for (int i = begin; i <= maxPage && i < begin + 5; i++) {
            pageList.add(i);
        }
        showFirstPage = !pageList.contains(1);
        showEndPage = !pageList.contains(maxPage);
    }
    
}
