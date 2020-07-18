package com.ktm.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CateGoryVo {


    private Integer id;

    private String name;

    private Integer parentId;

    private String description;

    private String parentName;
}
