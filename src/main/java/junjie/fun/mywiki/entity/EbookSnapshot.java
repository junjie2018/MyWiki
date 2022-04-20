package junjie.fun.mywiki.entity;

import junjie.fun.mywiki.common.entity.BaseEntity;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("t_ebook_snapshot")
public class EbookSnapshot extends BaseEntity {

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
