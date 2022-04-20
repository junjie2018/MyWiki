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
@TableName("t_doc")
public class Doc extends BaseEntity {

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
