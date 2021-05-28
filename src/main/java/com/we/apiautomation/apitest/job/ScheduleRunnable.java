package com.we.apiautomation.apitest.job;

import com.we.apiautomation.apitest.model.Job;
import com.we.apiautomation.apitest.utils.SpringUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

/**
 * 执行定时任务
 */
public class ScheduleRunnable implements Callable {
    private Object target;
    private Method method;
//    private String params;
    private Job job;

    public ScheduleRunnable(Job job)
            throws NoSuchMethodException, SecurityException {
        String methodName = job.getMethodName();
        this.target = SpringUtils.getBean(job.getJobName());
        this.job = job;
//        this.params = job.getMethodParams();
        this.method = target.getClass().getDeclaredMethod(methodName, Job.class);
//        if (job.getJobType().equals(1) || job.getJobType().equals(2) || job.getJobType().equals(3)) {
//        } else {
//            if (StringUtils.isNotEmpty(params)) {
//                this.method = target.getClass().getDeclaredMethod(methodName, String.class);
//            } else {
//                this.method = target.getClass().getDeclaredMethod(methodName);
//            }
//        }
    }

    @Override
    public String call() throws Exception {
        ReflectionUtils.makeAccessible(method);
        method.invoke(target, job);
//        if (job.getJobType().equals(1) || job.getJobType().equals(2) || job.getJobType().equals(3)) {
//        } else {
//            if (StringUtils.isNotEmpty(params)) {
//                method.invoke(target, params);
//            } else {
//                method.invoke(target);
//            }
//        }
        return "执行完毕";
    }
}
