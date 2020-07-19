package com.ktm.vo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentVo {

    private Integer id;

    private Integer articleid;

    /**
     * 1代表评论的文章存在
     0代表评论的文章已经不存在了
     */
    private Integer status;

    private String content;

    private LocalDateTime createTime;

    private String articleName;
}
