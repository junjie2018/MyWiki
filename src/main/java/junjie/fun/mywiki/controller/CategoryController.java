package junjie.fun.mywiki.controller;

import junjie.fun.mywiki.request.category.CreateCategoryRequest;
import junjie.fun.mywiki.request.category.UpdateCategoryRequest;
import junjie.fun.mywiki.request.ebook.CreateEBookRequest;
import junjie.fun.mywiki.request.ebook.UpdateEBookRequest;
import junjie.fun.mywiki.response.ResponseVo;
import junjie.fun.mywiki.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;


    @PostMapping("/category/createCategory")
    public ResponseVo<Long> createCategory(@Valid @RequestBody CreateCategoryRequest request) {
        return ResponseVo.success(categoryService.createCategory(request));
    }

    /**
     * 根据Id，删除分类
     *
     * @param categoryId categoryId主键
     */

    @PostMapping("/category/deleteCategory")
    public ResponseVo<Long> deleteCategory(@RequestParam("categoryId") Long categoryId) {
//        eBookService.deleteEBook(eBookId);
//
//        categoryService.removeById(categoryId);
//
//        return ResponseVo.success(userId);
//
//        return ResponseVo.success(eBookId);
        return null;
    }

    /**
     * 根据Id数组，批量删除分类
     *
     * @param categoryIds categoryId数组
     */
    @PostMapping("/category/deleteCategories")
    public ResponseVo<Long> tmp(@RequestBody List<Long> categoryIds) {
//        eBookService.deleteEBook(eBookId);
//
//        return ResponseVo.success(eBookId);
        return null;
    }

    @PostMapping("/category/updateCategory")
    public ResponseVo<Long> updateCategory(@Valid @RequestBody UpdateCategoryRequest request) {
        return ResponseVo.success(categoryService.updateCategory(request));
    }


}
