package ren.aiernory.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ren.aiernory.blog.model.User;

import java.util.List;


/**
 * @author Aiernory
 * @className: ren.aiernory.blog.controller.LabelController
 * @date 2020/3/28 22:37
 * @Description:
 */
@Controller
public class LabelController {
    //方法全部post
    
    /*
    大类目下小类目。官方只提供两种等级的标签。左边栏、可折叠；次元型
		文章创建时的标签可以跨大类目、也可以跨小类目
		查找时大类目显示：包含所有小类目。parent=大类目id。大类目的parent为-1
	自定义标签
		redis key-value。value支持list
			#复杂的
		key为文章+标签，value为点赞的用户。当value多余某个数，暂时为5。容易修改
				redis，将数据存入mysql，文章将永久支持该标签
					关闭服务器前redis持久化，开启时复原redis
				永久支持的标签，建立一个文章标签表
				    新建两个表：
				        标签实体表       官方提供的标签（计数属性设为-1） +  用户自定的标签（引用计数，0值定期回收，通过差文章标签关系表）
				        文章标签关系表
				            文章、标签 多对多。不重复、查询时只（主要）根据文章id查询。
				                 主键id+自增   文章id    标签id
				                                                （关于索引、完成了之后再去统一优化整改）
        
        可能需要处理的操作
            需要准备redis,..........学习准备两天
        
	先不管前台渲染、只管后端服务
     */
    
    

}
