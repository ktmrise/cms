package com.ktm.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ktm.common.Result;
import com.ktm.model.User;
import com.ktm.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

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


    /**
     * 用户登录
     * @param user
     * @return
     */
    @RequestMapping("/login")
    public Result login(User user) {
//        User dbUser = userService.getOne(new QueryWrapper<User>().eq("username", user.getUsername()));
//        if (user.getPassword().equals(dbUser.getPassword())) {
//            return Result.ok("登录成功", dbUser, 200);
//        }
//        throw new RuntimeException("用户不存在");
        Subject subject = SecurityUtils.getSubject();

        subject.login(new UsernamePasswordToken(user.getUsername(), user.getPassword()));
        if (subject.isAuthenticated()) {
            User dbUser = userService.getOne(new QueryWrapper<User>().eq("username", user.getUsername()));
            return Result.ok("登录成功", dbUser, 200);
        }
        return Result.fail();

    }


    /**
     * 退出登录
     * @return
     */
    @RequestMapping("/logout")
    public Result logout() {
        return Result.ok();
    }


    /**
     * 查询所有用户
     * @return
     */
    @GetMapping("/findAllUser")
    public Result findAll() {
        List<User> users = userService.findAllUser();
        return Result.ok(users);
    }


    /**
     * 保存或者更新用户
     * @param user
     * @return
     * @throws IOException
     */
    @RequestMapping("/saveOrUpdateUser")
    public Result saveOrUpdateUser(User user) throws IOException {


//        String oldName = photo.getOriginalFilename();
//        String newName = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(oldName);
//
//        String realPath = path + newName;
//        photo.transferTo(new File(realPath));
//        user.setUserFace(realPath);
//        String faceUrl = (String) request.getSession().getAttribute("faceUrl");
//        user.setUserFace(faceUrl);
        userService.saveOrUpdate(user);


        return Result.ok("success", null, 200);
    }
}
