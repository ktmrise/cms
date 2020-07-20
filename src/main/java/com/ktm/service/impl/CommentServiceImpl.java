package com.ktm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ktm.common.PageResult;
import com.ktm.mapper.ArticleMapper;
import com.ktm.model.Article;
import com.ktm.model.Comment;
import com.ktm.mapper.CommentMapper;
import com.ktm.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ktm.vo.CommentVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author author
 * @since 2020-07-19
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

    @Resource
    private CommentMapper commentMapper;

    @Resource
    private ArticleMapper articleMapper;

    @Override
    public PageResult findComment(int page, int row, String keyWords) {
        List<Article> articles = articleMapper.selectList(new QueryWrapper<Article>().like(keyWords != null, "title", keyWords));
        ArrayList<Integer> ids = (ArrayList<Integer>) articles.stream().map(Article::getId).collect(Collectors.toList());
        Page<Comment> commentPages = commentMapper.selectPage(new Page<>(page, row), new QueryWrapper<Comment>()
                .in(ids.size() > 0, "articleid", ids)
                .or().like(keyWords != null, "content", keyWords)
        );
        List<Comment> records = commentPages.getRecords();
        List<CommentVo> commentVos = new ArrayList<>();
        for (Comment record : records) {
            CommentVo commentVo = new CommentVo();
            Article article = articleMapper.selectById(record.getArticleid());
            BeanUtils.copyProperties(record, commentVo);
            commentVo.setArticleName(article.getTitle());
            commentVos.add(commentVo);
        }

        return new PageResult(commentVos, (int) commentPages.getTotal());
    }
}
