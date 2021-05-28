package com.we.apiautomation.apitest.job;


import com.we.apiautomation.apitest.model.Job;
import com.we.apiautomation.apitest.service.ExcApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 自动化定时任务
 */
@Slf4j
@Component("autoTestNewTask")
public class AutoTestNewTask {
//
//    @Autowired
//    private ExcUiService excUiService;

    @Resource
    private ExcApiService excApiService;

//    public void uiTest(Job job) throws Exception {
//        final ExecutorService exec = Executors.newFixedThreadPool(1);
//        Callable<String> call = new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                excUiService.excJob(job);
//                return "线程执行完成.";
//            }
//        };
//        exec.submit(call).get();
//    }

    public void apiTest(Job job) throws Exception {
        final ExecutorService exec = Executors.newFixedThreadPool(1);
        Callable<String> call = new Callable<String>() {
            @Override
            public String call() throws Exception {
                excApiService.excJob(job);
                return "线程执行完成.";
            }
        };
        exec.submit(call).get();
    }
}
