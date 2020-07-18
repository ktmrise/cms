package com.ktm.service.impl;

import com.ktm.model.Category;
import com.ktm.mapper.CategoryMapper;
import com.ktm.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ktm.vo.CateGoryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author author
 * @since 2020-07-17
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public List<CateGoryVo> findAllCategory() {


        List<CateGoryVo> cateGoryVoList = new ArrayList<>();


        List<Category> categoryList = categoryMapper.findAllCategory();
        if (categoryList == null) {
            throw new RuntimeException("查询结果为空");
        }
        for (Category category : categoryList) {
            CateGoryVo cateGoryVo = new CateGoryVo();
            BeanUtils.copyProperties(category, cateGoryVo);
            String parentName = categoryMapper.selectNameById(category.getParentId());
            cateGoryVo.setParentName(parentName);
            cateGoryVoList.add(cateGoryVo);
        }

        return cateGoryVoList;

    }

    @Override
    public int saveOrUpdateCategory(Category category) {
        int result;
        Category dbCateGory = categoryMapper.selectById(category.getId());
        if (dbCateGory == null) {
            result = categoryMapper.insert(category);
            if (result == 0) {
                throw new RuntimeException("保存失败");
            }
        }
        result = categoryMapper.updateById(category);
        if (result == 0) {
            throw new RuntimeException("更新失败");
        }
        return result;
    }
}
