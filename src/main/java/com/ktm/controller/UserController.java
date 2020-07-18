package com.ktm.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ktm.common.Result;
import com.ktm.model.User;
import com.ktm.service.IUserService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author author
 * @since 2020-07-17
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @Resource
    private IUserService userService;


    @RequestMapping("/login")
    public Result login(User user) {
        User dbUser = userService.getOne(new QueryWrapper<User>().eq("username", user.getUsername()));
        if (user.getPassword().equals(dbUser.getPassword())) {
            return Result.ok(dbUser,200);
        }
        throw new RuntimeException("用户不存在");

    }


    @RequestMapping("/logout")
    public Result logout() {
        return Result.ok();
    }
}
