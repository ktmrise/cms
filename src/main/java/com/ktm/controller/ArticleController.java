package com.ktm.controller;


import com.ktm.common.PageResult;
import com.ktm.common.Result;
import com.ktm.model.Article;
import com.ktm.service.IArticleService;
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


    /**
     * 查询所有文章
     *
     * @return
     */
    @GetMapping("/findArticle")
    public Result findArticle() {

        List<ArticleVo> articleVos = articleService.findArticle();

        return Result.ok(articleVos);
    }


    /**
     * 分页，按关键字（标题）查询文章
     *
     * @param page
     * @param row
     * @param keyWords
     * @param cateGoryId
     * @return
     */
    @GetMapping("/findArticlePage")
    public PageResult findArticlePage(@RequestParam("page") int page,
                                      @RequestParam("pageSize") int row,
                                      String keyWords, Integer cateGoryId) {

        return articleService.findArticlePage(page, row, keyWords, cateGoryId);

    }


    /**
     * 保存或者更新文章
     *
     * @param article
     * @return
     */
    @PostMapping("/saveOrUpdateArticle")
    public Result saveOrUpdateArticle(Article article) {

        articleService.saveOrUpdateArticle(article);

        return Result.ok("success", null, 200);
    }


    /**
     * 删除单条文章
     *
     * @param id
     * @return
     */
    @RequestMapping("/deleteArticleById")
    public Result deleteArticleById(Integer id) {

        articleService.removeById(id);

        articleService.removeRelatedComment(id);

        return Result.ok("删除成功", null, 200);
    }

    /**
     * 批量删除文章
     *
     * @param ids
     * @return
     */
    @PostMapping("/batchDeleteArticles")
    public Result batchDeleteArticles(Integer[] ids) {

        List<Integer> idList = Arrays.asList(ids);

        articleService.removeByIds(idList);

        articleService.removeRelatedComment(idList);


        return Result.ok("删除成功", null, 200);


    }


    /**
     * 更新文章查看次数
     *
     * @param id
     * @return
     */
    @PostMapping("/updateViewCount")
    public Result updateViewCount(int id) {

        synchronized (ArticleController.class) {
            articleService.updateViewCount(id);
        }

        return Result.ok();
    }

}
