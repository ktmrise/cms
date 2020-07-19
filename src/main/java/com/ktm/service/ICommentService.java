package com.ktm.service;

import com.ktm.common.PageResult;
import com.ktm.model.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2020-07-19
 */
public interface ICommentService extends IService<Comment> {

    PageResult findComment(int page, int row, String keyWords);
}
