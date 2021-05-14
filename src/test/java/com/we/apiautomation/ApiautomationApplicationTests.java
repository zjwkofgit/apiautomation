package com.we.apiautomation;

import com.google.common.collect.Sets;
import com.we.apiautomation.apitest.controller.ApiController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashSet;

@SpringBootTest
class ApiautomationApplicationTests {

    @Resource
    ApiController apiController;

    @Test
    void contextLoads() {
        HashSet<String> set = Sets.newHashSet("haole,hasdsa-asdasdagd");
        System.out.println(set);
    }
    @Test
    void contextLoads2() {
        apiController.getPageList(1,10,"{\"name\":\"\",\"apiSuiteId\":35,\"projectId\":1}");

    }

}
