package junjie.fun.mywiki.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import junjie.fun.mywiki.common.response.PageData;

import java.util.List;

import junjie.fun.mywiki.request.*;
import junjie.fun.mywiki.response.*;
import junjie.fun.mywiki.entity.*;

@Service
public interface CategoryService extends IService<Category> {

  /** 创建分类 */
  Long createCategory(CreateCategoryRequest request);

  /** 删除分类 */
  void deleteCategorys(List<Long> categoryIds);

  /** 编辑分类 */
  Long updateCategory(UpdateCategoryRequest request);

  /** 分页查找分类 */
  PageData<CategoryData> pageCategory(PageCategoryRequest request);

  /** 根据Id数组查找分类 */
  List<CategoryData> queryCategorys(List<Long> categoryIds);
}
