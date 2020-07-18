package com.ktm.mapper;

import com.ktm.model.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author author
 * @since 2020-07-17
 */
public interface CategoryMapper extends BaseMapper<Category> {

    List<Category> findAllCategory();

    String selectNameById(Integer parentId);
}
