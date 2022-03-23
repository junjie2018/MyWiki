package junjie.fun.mywiki.request.ebook;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateOrUpdateEBookRequest {
    private Long id;

    @NotNull(message = "name不能为空")
    private String name;

    private Long category1Id;

    private Long category2Id;

    private String description;

    private String cover;

    private Integer docCount;

    private Integer viewCount;

    private Integer voteCount;
}
