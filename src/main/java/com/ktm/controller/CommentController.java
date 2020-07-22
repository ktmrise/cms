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


    /**
     * 分页，按关键字查询评论
     * @param page
     * @param row
     * @param keyWords
     * @return
     */
    @GetMapping("/findComment")
    public PageResult findComment(@RequestParam("page") int page,
                                  @RequestParam("pageSize") int row,
                                  String keyWords) {
        return commentService.findComment(page, row, keyWords);
    }


    /**
     * 发布评论
     * @param comment
     * @return
     */
    @RequestMapping("/publishComment")
    public Result publishComment(Comment comment) {
        comment.setCreateTime(LocalDateTime.now());
        commentService.saveOrUpdate(comment);
        return Result.ok();
    }


    /**
     * 删除单条评论
     * @param id
     * @return
     */
    @RequestMapping("/deleteCommentById")
    public Result deleteCommentById(Integer id) {
        commentService.removeById(id);
        return Result.ok();
    }


    /**
     * 批量删除评论
     * @param ids
     * @return
     */
    @RequestMapping("/batchDeleteComment")
    public Result batchDeleteComment(Integer[] ids) {
        commentService.removeByIds(Arrays.asList(ids));
        return Result.ok();
    }
}
