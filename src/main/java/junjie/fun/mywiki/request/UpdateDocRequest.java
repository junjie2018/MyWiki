package junjie.fun.mywiki.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class UpdateDocRequest {

  /** id */
  @NotNull private Long id;

  /** 电子书id */
  private Long ebookId;

  /** 父id */
  private Long parent;

  /** 名称 */
  private String name;

  /** 顺序 */
  private Integer sort;

  /** 阅读数 */
  private Integer viewCount;

  /** 点赞数 */
  private Integer voteCount;
}
