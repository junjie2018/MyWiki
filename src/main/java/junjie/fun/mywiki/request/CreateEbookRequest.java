package junjie.fun.mywiki.request;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CreateEbookRequest {

  /** 名称 */
  private String name;

  /** 分类1 */
  private Long category1Id;

  /** 分类2 */
  private Long category2Id;

  /** 描述 */
  private String description;

  /** 封面 */
  private String cover;

  /** 文档数 */
  private Integer docCount;

  /** 阅读数 */
  private Integer viewCount;

  /** 点赞数 */
  private Integer voteCount;
}
