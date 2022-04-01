package junjie.fun.mywiki.request.category;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateOrUpdateCategoryRequest {
    /**
     * 主键Id
     */
    private Long id;
    /**
     * 父Id
     */
    private Long parentId;
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
