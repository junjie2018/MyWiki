package junjie.fun.mywiki.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Doc {
    private Long id;
    private Long eBookId;
    private Long parentId;
    private String name;
    private Integer sort;
    private Integer viewCount;
    private Integer voteCount;
}
