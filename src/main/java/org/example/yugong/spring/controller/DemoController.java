package org.example.yugong.spring.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.yugong.annotation.AutoLog;
import org.example.yugong.spring.entity.User;
import org.example.yugong.spring.event.UserRegisterPublisherService;
import org.example.yugong.spring.repository.UserRepository;
import org.example.yugong.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.management.Query;
import java.util.ArrayDeque;
import java.util.Date;
import java.util.List;
import java.util.Queue;

/**
 * @author qiaobao
 * @since 2020-8-31
 */
@RestController
@Api("测试")
public class DemoController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRegisterPublisherService userRegisterPublisherService;

    @ApiOperation("创建")
    @PostMapping("/user")
    @Async()
    @AutoLog(methodExplain = "创建用户")
    public void create(User user) {
        Queue<String> stringQueue = new ArrayDeque<>();
        user.setUpdateTime(new Date());
        user.setCreateTime(new Date());
        userRegisterPublisherService.insert(user);
        Thread thread = Thread.currentThread();
        System.out.println("主线任务结束" + thread.getName());
    }

    @ApiOperation("查询全部")
    @GetMapping("/all")
    @AutoLog
    public List<User> selectAll() {
        List<User> all = userRepository.findAll();
        return all;
    }


    @ApiOperation("根据姓名修改年龄")
    @RequestMapping(method = RequestMethod.PUT,value = "user/age")
    @AutoLog
    public User update(@RequestParam String name, @RequestParam Integer age) {
        User user = userRepository.findByUserName(name);
        user.setAge(age);
        user.setUpdateTime(new Date());
        userRepository.save(user);
        return user;
    }


    @ApiOperation("根据姓名修改年龄")
    @RequestMapping(method = RequestMethod.PUT,value = "user/test")
    @Transactional
    @AutoLog
    public void transactionTest(@RequestParam String name, @RequestParam Integer age) {
        User user = userRepository.findByUserName(name);
        user.setName("test1");
        userRepository.save(user);
        int[] strings = new int[]{1,2};

        try {
            userService.update(user);
        } catch (Exception e) {
            System.out.println("测试");
        }
        return;
    }


}
