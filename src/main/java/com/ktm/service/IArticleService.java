package com.ktm.service;

import com.ktm.common.PageResult;
import com.ktm.model.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ktm.vo.ArticleVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2020-07-18
 */
public interface IArticleService extends IService<Article> {

    List<ArticleVo> findArticle();

    PageResult findArticlePage(int page, int row,String keyWords,Integer cateGoryId);

    void saveOrUpdateArticle(Article article);

    void updateCommentStatus(Integer id);
}
