package com.ktm.controller;


import com.alibaba.fastjson.JSON;
import com.ktm.common.Result;
import com.ktm.model.Category;
import com.ktm.service.ICategoryService;
import com.ktm.vo.CateGoryVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
@CrossOrigin
public class CategoryController {


    @Resource
    private ICategoryService categoryService;


    @GetMapping("/findAllCategory")
    public Result findAllCategory() {
        List<CateGoryVo> cateGoryVoList = categoryService.findAllCategory();
        return Result.ok(cateGoryVoList);
    }


    @PostMapping("/saveOrUpdateCategory")
    public Result saveOrUpdateCategory(Category category) {
        int resultNumber = categoryService.saveOrUpdateCategory(category);
        return Result.ok("success",null,200);
    }

    @RequestMapping("/deleteCategoryById")
    public Result deleteCategoryById(int id) {
        boolean result = categoryService.removeById(id);
        if (result) {
            return Result.ok();
        }
        return Result.fail();
    }

    @PostMapping(value = "/batchDeleteCategory")
    public Result batchDeleteCategory(Integer[] ids) {
//        System.out.println(Arrays.toString(ids));
        List<Integer> list = Arrays.asList(ids);

        boolean result = categoryService.removeByIds(list);
        if (result) {
            return Result.ok();
        }
        return Result.fail();
    }
}
