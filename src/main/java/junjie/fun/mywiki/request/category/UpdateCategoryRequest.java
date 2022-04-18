package junjie.fun.mywiki.request.category;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UpdateCategoryRequest {
    /**
     * 主键Id
     */
    @NotNull
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
