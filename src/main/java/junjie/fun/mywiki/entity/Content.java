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
@TableName("t_content")
public class Content extends BaseEntity {

  /** 内容 */
  private String content;
}
