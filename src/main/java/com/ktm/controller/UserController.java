package com.ktm.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ktm.common.Result;
import com.ktm.model.User;
import com.ktm.service.IUserService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author author
 * @since 2020-07-17
 */
@RestController
@RequestMapping("/manager/user")
public class UserController {


    @Resource
    private IUserService userService;

    @Value("photo.path")
    private String path;

    @RequestMapping("/login")
    public Result login(User user) {
        User dbUser = userService.getOne(new QueryWrapper<User>().eq("username", user.getUsername()));
        if (user.getPassword().equals(dbUser.getPassword())) {
            return Result.ok("登录成功", dbUser, 200);
        }
        throw new RuntimeException("用户不存在");

    }


    @RequestMapping("/logout")
    public Result logout() {
        return Result.ok();
    }


    @GetMapping("/findAllUser")
    public Result findAll() {
        List<User> users = userService.findAllUser();
        return Result.ok(users);
    }


    @RequestMapping("/saveOrUpdateUser")
    public Result saveOrUpdateUser(User user) throws IOException {


//        String oldName = photo.getOriginalFilename();
//        String newName = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(oldName);
//
//        String realPath = path + newName;
//        photo.transferTo(new File(realPath));
//        user.setUserFace(realPath);
        userService.saveOrUpdate(user);


        return Result.ok("success", null, 200);
    }
}
