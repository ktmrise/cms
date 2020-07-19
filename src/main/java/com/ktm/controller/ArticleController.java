package com.ktm.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ktm.common.PageResult;
import com.ktm.common.Result;
import com.ktm.mapper.CommentMapper;
import com.ktm.model.Article;
import com.ktm.model.Comment;
import com.ktm.service.IArticleService;
import com.ktm.service.ICommentService;
import com.ktm.vo.ArticleVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author author
 * @since 2020-07-18
 */
@RestController
@RequestMapping("/manager/article")
public class ArticleController {


    @Resource
    private IArticleService articleService;




    @GetMapping("/findArticle")
    public Result findArticle() {
        List<ArticleVo> articleVos = articleService.findArticle();
        return Result.ok(articleVos);
    }


    @GetMapping("/findArticlePage")
    public PageResult findArticlePage(@RequestParam("page") int page,
                                      @RequestParam("pageSize") int row,
                                      String keyWords, Integer cateGoryId) {
        return articleService.findArticlePage(page, row, keyWords, cateGoryId);
    }


    @PostMapping("/saveOrUpdateArticle")
    public Result saveOrUpdateArticle(Article article) {
        articleService.saveOrUpdateArticle(article);
        return Result.ok("success", null, 200);
    }


    @RequestMapping("/deleteArticleById")
    public Result deleteArticleById(Integer id) {
        articleService.removeById(id);
        articleService.updateCommentStatus(id);

        return Result.ok("删除成功", null, 200);
    }

    @PostMapping("/batchDeleteArticles")
    public Result batchDeleteArticles(Integer[] ids) {
        List<Integer> idList = Arrays.asList(ids);
        boolean result = articleService.removeByIds(idList);
        if (result) {
            return Result.ok("删除成功", null, 200);
        }
        return Result.fail("删除失败",null,500);
    }
}
