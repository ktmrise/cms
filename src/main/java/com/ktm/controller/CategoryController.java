package com.ktm.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ktm.common.Result;
import com.ktm.model.Article;
import com.ktm.model.Category;
import com.ktm.model.Comment;
import com.ktm.service.IArticleService;
import com.ktm.service.ICategoryService;
import com.ktm.service.ICommentService;
import com.ktm.vo.CateGoryVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author author
 * @since 2020-07-17
 */
@RestController
@RequestMapping("/manager/category")
public class CategoryController {


    @Resource
    private ICategoryService categoryService;


    @Resource
    private ArticleController articleController;


    @Resource
    private IArticleService articleService;

    @Resource
    private ICommentService commentService;


    /**
     * 查询所有栏目
     *
     * @return
     */
    @GetMapping("/findAllCategory")
    public Result findAllCategory() {

        List<CateGoryVo> cateGoryVoList = categoryService.findAllCategory();

        return Result.ok(cateGoryVoList);
    }


    /**
     * 保存或者更新栏目
     *
     * @param category
     * @return
     */
    @PostMapping("/saveOrUpdateCategory")
    public Result saveOrUpdateCategory(Category category) {

        int resultNumber = categoryService.saveOrUpdateCategory(category);

        return Result.ok("success", null, 200);
    }

    /**
     * 删除单个栏目
     *
     * @param id
     * @return
     */
    @RequestMapping("/deleteCategoryById")
    public Result deleteCategoryById(int id) {
        List<Category> categories = categoryService.list(new QueryWrapper<Category>().eq("parentId", id));
        if (categories.size() > 0) {
            return Result.fail("你要删除的栏目包含子栏目，不能删除", null, 500);
        }

        categoryService.removeById(id);

        List<Article> articles = articleService.list(new QueryWrapper<Article>().eq("categoryId", id));
        if (articles.size() == 0) {
            return Result.ok();
        }
        articleService.remove(new QueryWrapper<Article>().eq("categoryId", id));

        List<Article> articleList = articleService.list(new QueryWrapper<Article>().eq("categoryId", id));
        if (articleList.size() == 0) {
            return Result.ok();
        }
        List<Integer> integerList = articleList
                .stream().map(e -> e.getId()).collect(Collectors.toList());


        commentService.remove(new QueryWrapper<Comment>().in("articleid", integerList));


        return Result.ok();
    }


    /**
     * 批量删除栏目
     *
     * @param ids
     * @return
     */
    @PostMapping(value = "/batchDeleteCategory")
    public Result batchDeleteCategory(Integer[] ids) {

        List<Integer> list = Arrays.asList(ids);


        List<Category> categories = categoryService.list(new QueryWrapper<Category>().in("parentId", list));

        if (categories.size() > 0) {
            return Result.fail("你要删除的某些栏目里包含子栏目，不能删除", null, 500);
        }

        categoryService.removeByIds(list);

        List<Article> articles = articleService.list(new QueryWrapper<Article>().in("categoryId", list));
        if (articles.size() == 0) {
            return Result.ok();
        }
        articleService.remove(new QueryWrapper<Article>().in("categoryId", list));

        List<Article> articleList = articleService.list(new QueryWrapper<Article>().in("categoryId", list));
        if (articleList.size() == 0) {
            return Result.ok();
        }

        List<Integer> integerList = articleList
                .stream().map(e -> e.getId()).collect(Collectors.toList());


        commentService.remove(new QueryWrapper<Comment>().in("articleid", integerList));

        return Result.ok();
    }
}
