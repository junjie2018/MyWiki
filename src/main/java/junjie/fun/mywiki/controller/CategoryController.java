package junjie.fun.mywiki.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import junjie.fun.mywiki.common.response.PageData;
import junjie.fun.mywiki.common.response.ResponseVo;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

import junjie.fun.mywiki.request.*;
import junjie.fun.mywiki.response.*;
import junjie.fun.mywiki.service.*;

/** 分类管理 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class CategoryController {

  private final CategoryService categoryService;

  /** 分页查询Category */
  @PostMapping("/category/pageCategory")
  public ResponseVo<PageData<CategoryData>> pageCategory(
      @Valid @RequestBody PageCategoryRequest request) {
    return ResponseVo.success(categoryService.pageCategory(request));
  }

  /** 创建Category */
  @PostMapping("/category/createCategory")
  public ResponseVo<Long> createCategory(@Valid @RequestBody CreateCategoryRequest request) {
    return ResponseVo.success(categoryService.createCategory(request));
  }

  /** 更新Category */
  @PostMapping("/category/updateCategory")
  public ResponseVo<Long> updateCategory(@Valid @RequestBody UpdateCategoryRequest request) {
    return ResponseVo.success(categoryService.updateCategory(request));
  }

  /**
   * 根据Id查询Category
   *
   * @param categoryId Category的主键Id
   */
  @PostMapping("/category/queryCategory")
  public ResponseVo<CategoryData> queryCategory(@RequestParam("categoryId") Long categoryId) {

    List<CategoryData> categoryData =
        categoryService.queryCategorys(Collections.singletonList(categoryId));

    return ResponseVo.success(
        CollectionUtils.isNotEmpty(categoryData) ? categoryData.get(0) : null);
  }

  /**
   * 根据Id数组批量查询Category
   *
   * @param categoryIds Category的主键Id数组
   */
  @PostMapping("/category/queryCategorys")
  public ResponseVo<List<CategoryData>> queryCategorys(@RequestBody List<Long> categoryIds) {

    return ResponseVo.success(categoryService.queryCategorys(categoryIds));
  }

  /**
   * 根据Id删除Category
   *
   * @param categoryId Category的主键Id
   */
  @PostMapping("/category/deleteCategory")
  public ResponseVo<Long> deleteCategory(@RequestParam("categoryId") Long categoryId) {
    categoryService.deleteCategorys(Collections.singletonList(categoryId));

    return ResponseVo.success();
  }

  /**
   * 根据Id数组批量删除Category
   *
   * @param categoryIds Category的主键Id数组
   */
  @PostMapping("/category/deleteCategorys")
  public ResponseVo<Void> deleteCategorys(@RequestBody List<Long> categoryIds) {

    categoryService.deleteCategorys(categoryIds);

    return ResponseVo.success();
  }
}
