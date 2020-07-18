package com.ktm.service;

import com.ktm.model.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2020-07-18
 */
public interface IUserService extends IService<User> {

    List<User> findAllUser();

}
