package com.ktm.controller;


import com.ktm.common.PageResult;
import com.ktm.common.Result;
import com.ktm.model.Article;
import com.ktm.service.IArticleService;
import com.ktm.vo.ArticleVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
    public PageResult findArticlePage(@RequestParam("page") int page, @RequestParam("pageSize") int row) {
        return articleService.findArticlePage(page, row);
    }


    @PostMapping("/saveOrUpdateArticle")
    public Result saveOrUpdateArticle(Article article) {
        articleService.saveOrUpdateArticle(article);
        return Result.ok("success",null,200);
    }
}
