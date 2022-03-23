package junjie.fun.mywiki.request.category;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateOrUpdateCategoryRequest {
    private Long id;
    private Long parent;
    @NotBlank(message = "name不可为空")
    private String name;
    @NotBlank(message = "sort不可为空")
    private Integer sort;
}
