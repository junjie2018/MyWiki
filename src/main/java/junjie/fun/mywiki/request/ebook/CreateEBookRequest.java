package junjie.fun.mywiki.request.ebook;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateEBookRequest {
    @NotNull
    private String name;

    private Long category1Id;

    private Long category2Id;

    private String description;

    private String cover;
}
