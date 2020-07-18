package com.ktm.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author author
 * @since 2020-07-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Article implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String title;

    @TableField("categoryId")
    private Integer categoryId;

    private String author;

    private LocalDateTime createTime;

    @TableField("viewCount")
    private Integer viewCount;

    private String content;


}
