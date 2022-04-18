package junjie.fun.mywiki.request.ebook;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UpdateEBookRequest {
    @NotNull
    private Long id;

    @NotBlank
    private String name;

    private Long category1Id;

    private Long category2Id;

    private String description;

    private String cover;
}
