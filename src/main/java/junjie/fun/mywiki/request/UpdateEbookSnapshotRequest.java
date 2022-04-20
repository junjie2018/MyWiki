package junjie.fun.mywiki.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class UpdateEbookSnapshotRequest {

  /** id */
  @NotNull private Long id;

  /** 电子书id */
  private Long ebookId;

  /** 快照日期 */
  private LocalDateTime date;

  /** 阅读数 */
  private Integer viewCount;

  /** 点赞数 */
  private Integer voteCount;

  /** 阅读增长 */
  private Integer viewIncrease;

  /** 点赞增长 */
  private Integer voteIncrease;
}
