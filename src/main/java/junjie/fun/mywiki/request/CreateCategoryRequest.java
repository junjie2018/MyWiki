package junjie.fun.mywiki.request;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CreateCategoryRequest {

  /** 父id */
  private Long parent;

  /** 名称 */
  private String name;

  /** 顺序 */
  private Integer sort;
}
