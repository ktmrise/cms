package com.ktm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ktm.common.PageResult;
import com.ktm.model.Article;
import com.ktm.mapper.ArticleMapper;
import com.ktm.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ktm.vo.ArticleVo;
import org.springframework.asm.RecordComponentVisitor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author author
 * @since 2020-07-18
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {


    @Resource
    private ArticleMapper articleMapper;

    @Override
    public List<ArticleVo> findArticle() {
        List<Article> articles = articleMapper.findArticle();
        List<ArticleVo> articleVos = new ArrayList<>();
        for (Article article : articles) {
            ArticleVo articleVo = new ArticleVo();
            String cateGoryName = articleMapper.findCategoryNameByCategoryId(article.getCategoryId());
            articleVo.setCateGoryName(cateGoryName);
            BeanUtils.copyProperties(article, articleVo);
            articleVos.add(articleVo);
        }
        return articleVos;
    }

    @Override
    public PageResult findArticlePage(int page, int row) {
        Page<Article> articlePage = articleMapper.selectPage(new Page<>(page, row), null);
        List<Article> records = articlePage.getRecords();
        ArrayList<ArticleVo> articleVos = new ArrayList<>();
        for (Article record : records) {
            ArticleVo articleVo = new ArticleVo();
            String cateGoryName = articleMapper.findCategoryNameByCategoryId(record.getCategoryId());
            articleVo.setCateGoryName(cateGoryName);
            BeanUtils.copyProperties(record, articleVo);
            articleVos.add(articleVo);
        }
        int total = (int) articlePage.getTotal();

        return new PageResult(articleVos, total);
    }

    @Override
    public void saveOrUpdateArticle(Article article) {
        Article dbArticle = articleMapper.selectById(article.getId());
        if (dbArticle == null) {
            article.setCreateTime(LocalDateTime.now());
            article.setViewCount(0);
            articleMapper.insert(article);
        } else {
            articleMapper.updateById(article);
        }
    }

}
