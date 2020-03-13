package ren.aiernory.blog.dto;

import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import ren.aiernory.blog.model.Publish;
import ren.aiernory.blog.service.PublishService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Aiernory
 * @className: ren.aiernory.blog.dto.PageHelper
 * @date 2020/3/11 23:30
 * @Description:
 */
@Data
public class PageHelper {
    
    

    private Boolean showPrevious;
    private Boolean showFirstPage;
    private Boolean showNext;
    private Boolean showEndPage;
    
    private Integer maxPage;
    private Integer currentPage;
    
    private List<Publish> publishes;
    private List<Integer> pageList;

    
    public void setPageList(int totalCount, int size, int page) {
      
        maxPage = (int) Math.ceil((double) totalCount / size);
        int begin = 1;
        
        showPrevious = !currentPage.equals(1);
        showNext = !currentPage.equals(maxPage);
        
        pageList = new ArrayList<>();
        if (maxPage <= 5) {
            begin = 1;
        } else if (currentPage >= 3 && currentPage <= maxPage - 2) {
            begin=currentPage-2;
        } else{
            if(currentPage<=2){
                begin=1;
            }else if(currentPage>=maxPage-2){
                begin=maxPage-4;
            }
        }
        
        for (int i = begin; i <= maxPage && i < begin + 5; i++) {
            pageList.add(i);
        }
        showFirstPage = !pageList.contains(1);
        showEndPage = !pageList.contains(maxPage);
    }
    
}
