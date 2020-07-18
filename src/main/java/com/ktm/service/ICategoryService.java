package com.ktm.service;

import com.ktm.model.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ktm.vo.CateGoryVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2020-07-17
 */
public interface ICategoryService extends IService<Category> {


    List<CateGoryVo> findAllCategory();

    int saveOrUpdateCategory(Category category);
}
