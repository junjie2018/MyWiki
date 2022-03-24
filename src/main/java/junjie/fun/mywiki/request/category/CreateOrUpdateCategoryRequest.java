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
     * 
     */
    private Long parentId;
    @NotBlank
    private String name;
    @NotBlank
    private Integer sort;
}
