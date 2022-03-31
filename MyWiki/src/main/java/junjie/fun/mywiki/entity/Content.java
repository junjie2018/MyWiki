package junjie.fun.mywiki.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Content {
    private Long id;
    private Long eBookId;
    private Long docId;
    private String content;
}
