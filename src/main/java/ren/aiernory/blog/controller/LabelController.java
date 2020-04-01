package ren.aiernory.blog.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;
import ren.aiernory.blog.enums.ErrorCodeEnum;
import ren.aiernory.blog.model.User;
import ren.aiernory.blog.resultMessage.ErrorMessage;
import ren.aiernory.blog.service.LabelService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


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
    //   key                文章与对应label                存入用户id      用户id10个以上，持久化到mysql
    //   article_label      1:3                          若干用户id
    //                      文章id1：labelid3
    
    //防止用户添加重复的标签。前台js判断
    
    //key   文章：标签组合value   用户id
    //1         n               m
    //不要这个key，则只能存储这么一种数据。set数据类型
    //要key，还可以做其他东西的缓存。map数据类型，其中value又为集合
    
    //决定，不要key了，该redis只存储标签喜欢信息。若整缓存，再去了解集成多个redis
    //
    
    
    //完全继承自Jedis
    //手动注入...
    @Autowired
    private Jedis myJedis;
    @Autowired
    private LabelService labelService;
    @Value("${redis.label.persist}")
    private Integer labelThreshold;
    private static Object lockObj=new Object();
    
    //添加标签。直接加到redis..修改set键的名称:  1:java  1
    @ResponseBody
    @PostMapping("/label")
    public String addLabel(@RequestBody String body,
                           HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            throw new ErrorMessage(ErrorCodeEnum.OPTION_NEED_LOGIN);
        }
        JSONObject obj = JSONObject.parseObject(body);
        Integer articleId = obj.getInteger("articleId");
        String labelName = obj.getString("labelName");
        
        String key = articleId + ":" + labelName;
        Long sadd;
        synchronized (lockObj){
            sadd = myJedis.sadd(key, user.getId().toString());
        }
        if(sadd==0){
            return "";
        }
        return labelName;
    }
    
    /**
     * 验证登录后且有不持久的标签是调用。返回map key为标签的name value为1喜欢、0不喜欢
     *
     * @param
     * @param request
     * @return
     */
    @ResponseBody
    @PostMapping("/gUserLabel")
    public String userLabelStatus(@RequestBody String body, HttpServletRequest request) {
        JSONObject obj = JSONObject.parseObject(body);
        Integer articleId = obj.getInteger("articleId");
        
        Map<String, Integer> map = new HashMap<>();
        User user = (User) request.getSession().getAttribute("user");
        
        //下面那个方法的结果，前台验证用户是否登录后，调用这个方法
        
        synchronized (lockObj){
            Set<String> keys = myJedis.keys("" + articleId + ":*");
            //差redis库，k
            keys.forEach(key -> {
                String name = key.split(":")[1];
                if (myJedis.sismember(key, user.getId().toString())) {
                    map.put(name, 1);
                } else {
                    map.put(name, 0);
                }
            });
        }
        return JSON.toJSONString(map);
    }
    
    
    /**
     * redis中找到文章的标签.未持久的标签
     *
     * @param
     * @return
     */
    @ResponseBody
    @PostMapping("/gTempLabel")
    public String getTempLabel(@RequestBody String body) {
        
        JSONObject obj = JSONObject.parseObject(body);
        Integer articleId = obj.getInteger("articleId");
        
        //键为label的名字，值为喜欢的总人数
        Map<String, Integer> map = new HashMap<>();
   
        //差redis库，找到未持久化的标签。
        //通配符，只在网上看到介绍，还没实际验证
        
        synchronized (lockObj){
            Set<String> keys = myJedis.keys("" + articleId + ":*");
            if (keys != null) {
                keys.forEach(
                        key -> {
                            String name = key.split(":")[1];
                            map.put(name, myJedis.scard(key).intValue());
                        }
                );
            }
        }
        return JSON.toJSONString(map);
    }
    
    
    /**
     * 文章加载时，载入文章持久化的标签      。还有一个方法是载入未持久化的标签。还有一个方法是，登录用户是否喜欢这些未持旧标签。分开返回值好写一点
     *
     * @param
     * @return
     */
    @ResponseBody
    @PostMapping("/gLabel")
    public String getLabel(@RequestBody String body) {
        
        JSONObject obj = JSONObject.parseObject(body);
        Integer articleId = obj.getInteger("articleId");
        //差mysql库，找到持久化的标签。
        List<String> names = labelService.getLabelNameByArticleId(articleId);
        return JSON.toJSONString(names);
    }
    
    /**
     * 当登录用户点了某个未持久化的标签后
     *
     * @param request
     * @return -1:持久化标签    1&2 1表示当前用户喜欢，标签喜欢的人数为2
     */
    @ResponseBody
    @PutMapping("/label")
    public String likeLabel(@RequestBody String body,
                            HttpServletRequest request) {
        JSONObject obj = JSONObject.parseObject(body);
        Integer articleId = obj.getInteger("articleId");
        String labelName = obj.getString("labelName");
        
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            //该操作需要登陆
            throw new ErrorMessage(ErrorCodeEnum.OPTION_NEED_LOGIN);
        }
        
        String uId = user.getId().toString();
        String key = articleId + ":" + labelName;
        //喜欢这个标签
        int like = 1;
        //喜欢这个标签的人数
        int count;
        
        //当前需要登录状态。从中获取user。
        //参数文章id，和标签的id
        //文章id和标签id组成键，user id为值，存入set。
        synchronized (lockObj){
            Long sadd = myJedis.sadd(key, uId);
            if (sadd == 0) {
                //成功是1，重复是0.如果0，删除信息
                myJedis.srem(key, uId);
                like = 0;
            }
            count = myJedis.scard(key).intValue();
        }
        if (count >= labelThreshold) {
            //持久化，存库
            //添加文章标签。参数文章id，标签id
            labelService.addArticleLabel(articleId, labelName);
            //标签持久,移除redis信息
            myJedis.del(key);
            return "-1";
        }
        //检测是否大于某个阈值
        return "" + like + ":" + count;
    }
    
}
