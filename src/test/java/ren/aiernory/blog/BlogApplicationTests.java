package ren.aiernory.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ren.aiernory.blog.model.Publish;
import ren.aiernory.blog.service.PublishService;

import java.util.List;

@SpringBootTest
class BlogApplicationTests {

    @Test
    void contextLoads() {
    }
    
    @Autowired
    PublishService publishService;
    
    @Test
    void crudTest(){

    }

}
