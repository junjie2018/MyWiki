package junjie.fun.mywiki.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EBookSnapshot {
    private Long id;
    private Long ebookId;
    private Date date;
    private Integer viewCount;
    private Integer voteCount;
    private Integer viewIncrease;
    private Integer voteIncrease;
}
