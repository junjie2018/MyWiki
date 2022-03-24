package junjie.fun.mywiki.request.doc;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateOrUpdateDocRequest {
    private Long id;

    @NotNull
    private Long ebookId;

    @NotNull
    private Long parentId;

    @NotNull
    private String name;

    @NotNull
    private Integer sort;

    private Integer viewCount;

    private Integer voteCount;

    @NotNull
    private String content;
}
