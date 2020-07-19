package com.ktm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ktm.common.PageResult;
import com.ktm.mapper.CommentMapper;
import com.ktm.model.Article;
import com.ktm.mapper.ArticleMapper;
import com.ktm.model.Comment;
import com.ktm.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ktm.vo.ArticleVo;
import org.springframework.asm.RecordComponentVisitor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author author
 * @since 2020-07-18
 */
@Service
@Transactional
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {


    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private CommentMapper commentMapper;

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
    public PageResult findArticlePage(int page, int row, String keyWords, Integer cateGoryId) {
        Page<Article> articlePage = articleMapper.selectPage(new Page<>(page, row), new QueryWrapper<Article>()
                .like(keyWords != null, "title", keyWords)
                .eq(cateGoryId != null, "categoryId", cateGoryId));
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

    @Override
    public void removeRelatedComment(Integer id) {
        List<Comment> comments = commentMapper.selectList(new QueryWrapper<Comment>().eq("articleid", id));
        List<Integer> ids = comments.stream().map(Comment::getId).collect(Collectors.toList());
        if (ids.size()>0) {
            commentMapper.deleteBatchIds(ids);
        }

    }

}
