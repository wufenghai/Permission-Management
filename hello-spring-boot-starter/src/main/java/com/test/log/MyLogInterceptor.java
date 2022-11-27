package com.test.log;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 自定义日志拦截器
 */
public class MyLogInterceptor extends HandlerInterceptorAdapter {
    private static final ThreadLocal<Long> startTimeThreadLocal = new ThreadLocal<>();

    //在controller方法执行前执行此方法
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;//处理器方法
        Method method = handlerMethod.getMethod();//获得被拦截的方法对象
        MyLog annotation = method.getAnnotation(MyLog.class);//获取加的注解
        if (annotation != null) {
            //说明当前拦截到的方法上加入了MyLog注解
            Long startTime = System.currentTimeMillis();
            startTimeThreadLocal.set(startTime);
        }

        return true;
    }

    //在controller方法执行后执行此方法
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;//处理器方法
        Method method = handlerMethod.getMethod();//获得被拦截的方法对象
        MyLog annotation = method.getAnnotation(MyLog.class);//获取加的注解
        if (annotation != null) {
            //说明当前拦截到的方法上加入了MyLog注解
            Long startTime = startTimeThreadLocal.get();
            Long endTime = System.currentTimeMillis();
            Long optTime = endTime - startTime;//操作时间，计算Controller方法执行的时间

            String requestURI = request.getRequestURI();
            String methodName = method.getDeclaringClass().getName() + "-" + method.getName();
            String methodDesc = annotation.desc();
            System.out.println("请求uri：" + requestURI);
            System.out.println("请求方法名：" + methodName);
            System.out.println("方法描述：" + methodDesc);
            System.out.println("方法执行时间：" + optTime + "ms");
        }

        super.postHandle(request, response, handler, modelAndView);
    }
}
