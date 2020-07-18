package com.ktm.mapper;

import com.ktm.model.Article;
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
public interface ArticleMapper extends BaseMapper<Article> {
    List<Article> findArticle();

    String findCategoryNameByCategoryId(Integer categoryId);
}
