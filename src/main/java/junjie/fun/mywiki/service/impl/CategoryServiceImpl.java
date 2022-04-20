package junjie.fun.mywiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import junjie.fun.mywiki.common.response.PageData;
import junjie.fun.mywiki.utils.CopyUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import junjie.fun.mywiki.request.*;
import junjie.fun.mywiki.response.*;
import junjie.fun.mywiki.entity.*;
import junjie.fun.mywiki.service.*;
import junjie.fun.mywiki.mapper.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService {

  @Override
  public Long createCategory(CreateCategoryRequest request) {
    Category eBookInert =
        Category.builder()
            .parent(request.getParent())
            .name(request.getName())
            .sort(request.getSort())
            .build();

    this.baseMapper.insert(eBookInert);

    return eBookInert.getId();
  }

  @Override
  public void deleteCategorys(List<Long> categoryIds) {
    this.baseMapper.deleteBatchIds(categoryIds);
  }

  @Override
  public Long updateCategory(UpdateCategoryRequest request) {
    LambdaUpdateWrapper<Category> updateWrapper =
        new LambdaUpdateWrapper<Category>()
            .eq(Category::getId, request.getId())
            .set(Category::getParent, request.getParent())
            .set(Category::getName, request.getName())
            .set(Category::getSort, request.getSort());

    this.baseMapper.update(null, updateWrapper);

    return request.getId();
  }

  @Override
  public PageData<CategoryData> pageCategory(PageCategoryRequest request) {

    Page<Category> pageEntity = request.getPage(Category.class);
    PageCategoryRequest.Condition condition = request.getCondition();

    LambdaQueryWrapper<Category> queryWrapper =
        new LambdaQueryWrapper<Category>().orderByDesc(Category::getCreateTime);

    baseMapper.selectPage(pageEntity, queryWrapper);

    return CopyUtils.copyPageData(pageEntity, CategoryData.class);
  }

  @Override
  public List<CategoryData> queryCategorys(List<Long> categoryIds) {

    LambdaQueryWrapper<Category> queryWrapper =
        new LambdaQueryWrapper<Category>().in(Category::getId, categoryIds);

    return CopyUtils.copyList(baseMapper.selectList(queryWrapper), CategoryData.class);
  }
}
