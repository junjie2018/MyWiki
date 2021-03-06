package junjie.fun.mywiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import junjie.fun.mywiki.entity.Category;
import junjie.fun.mywiki.mapper.CategoryMapper;
import junjie.fun.mywiki.request.PageRequest;
import junjie.fun.mywiki.request.category.CreateCategoryRequest;
import junjie.fun.mywiki.request.category.UpdateCategoryRequest;
import junjie.fun.mywiki.request.condition.PageCategoryCondition;
import junjie.fun.mywiki.response.data.CategoryData;
import junjie.fun.mywiki.service.CategoryService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    public Long createCategory(CreateCategoryRequest request) {
        Category categoryInsert = Category.builder()
                .parentId(request.getParentId())
                .name(request.getName())
                .sort(request.getSort())
                .build();

        baseMapper.insert(categoryInsert);

        return categoryInsert.getId();
    }

    public Long updateCategory(UpdateCategoryRequest request) {
        LambdaUpdateWrapper<Category> updateWrapper = new LambdaUpdateWrapper<Category>()
                .eq(Category::getId, request.getId())
                .set(Category::getParentId, request.getParentId())
                .set(Category::getName, request.getName())
                .set(Category::getSort, request.getSort());

        baseMapper.update(null, updateWrapper);

        return request.getId();
    }

    public Page<CategoryData> pageCategory(PageRequest<PageCategoryCondition> request) {


//
//        Page<Category> pageEntity = buildPage(request, Category.class);
//
//
//        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<Category>()
//                .orderByAsc(Category::getSort);
//
//        baseMapper.selectPage(pageEntity, queryWrapper);

//        return CopyUtils.copyPage(pageEntity, CategoryData.class);

        return null;
    }

}
