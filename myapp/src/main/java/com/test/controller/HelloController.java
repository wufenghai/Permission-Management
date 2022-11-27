package com.test.controller;

import com.test.log.MyLog;
import com.test.service.HelloService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
@RequiredArgsConstructor
public class HelloController {

//    @Autowired
//    private HelloService helloService;//当前实例已经由我们自定义的starter完成了创建

    private final HelloService helloService;//使用@RequiredArgsConstructor注入

    @MyLog(desc = "sayHello方法")//自定义接口注解
    @GetMapping("/say")
    public String sayHello() {
        return helloService.sayHello();
    }


}
