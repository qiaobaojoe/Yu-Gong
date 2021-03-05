package org.example.yugong.spring.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.yugong.spring.entity.User;
import org.example.yugong.spring.event.UserRegisterPublisherService;
import org.example.yugong.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

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
    private UserRegisterPublisherService userRegisterPublisherService;

    @ApiOperation("创建")
    @PostMapping("/user")
    public void create(User user) {
        user.setUpdateTime(new Date());
        user.setCreateTime(new Date());
        userRegisterPublisherService.insert(user);
    }

    @ApiOperation("查询全部")
    @GetMapping("/all")
    public List<User> selectAll() {
        List<User> all = userRepository.findAll();
        return all;
    }


    @ApiOperation("根据姓名修改年龄")
    @RequestMapping(method = RequestMethod.PUT,value = "user/age")
    public User update(@RequestParam String name, @RequestParam Integer age) {
        User user = userRepository.findByUserName(name);
        user.setAge(age);
        user.setUpdateTime(new Date());
        userRepository.save(user);
        return user;
    }


}
