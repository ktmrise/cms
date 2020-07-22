package com.ktm.mapper;

import com.ktm.model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author author
 * @since 2020-07-18
 */
public interface UserMapper extends BaseMapper<User> {

    List<User> findAllUser();

    User findUserByName(String name);
}
