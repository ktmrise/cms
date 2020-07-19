package com.ktm.controller;


import com.ktm.common.PageResult;
import com.ktm.common.Result;
import com.ktm.model.Comment;
import com.ktm.service.ICommentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author author
 * @since 2020-07-19
 */
@RestController
@RequestMapping("/manager/comment")
public class CommentController {


    @Resource
    private ICommentService commentService;


    @GetMapping("/findComment")
    public PageResult findComment(@RequestParam("page") int page,
                                  @RequestParam("pageSize") int row,
                                  String keyWords) {
        return commentService.findComment(page, row, keyWords);
    }


    @RequestMapping("/publishComment")
    public Result publishComment(Comment comment) {
        comment.setCreateTime(LocalDateTime.now());
        commentService.saveOrUpdate(comment);
        return Result.ok();
    }

    @RequestMapping("/deleteCommentById")
    public Result deleteCommentById(Integer id) {
        commentService.removeById(id);
        return Result.ok();
    }

    @RequestMapping("/batchDeleteComment")
    public Result batchDeleteComment(Integer[] ids) {
        commentService.removeByIds(Arrays.asList(ids));
        return Result.ok();
    }
}
