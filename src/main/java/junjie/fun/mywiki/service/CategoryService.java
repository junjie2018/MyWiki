package junjie.fun.mywiki.service;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import junjie.fun.mywiki.entity.Category;
import junjie.fun.mywiki.request.category.CreateCategoryRequest;
import junjie.fun.mywiki.request.category.UpdateCategoryRequest;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService extends IService<Category> {
    Long createCategory(CreateCategoryRequest request);

    Long updateCategory(UpdateCategoryRequest request);
}
