package junjie.fun.mywiki.request.category;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateCategoryRequest {
    /**
     * 父Id
     */
    private Long parentId = 0L;
    /**
     * 目录名称
     */
    @NotBlank
    private String name;
    /**
     * 排序
     */
    @NotBlank
    private Integer sort;
}
