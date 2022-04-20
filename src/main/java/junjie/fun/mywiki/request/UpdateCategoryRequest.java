package junjie.fun.mywiki.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class UpdateCategoryRequest {

  /** id */
  @NotNull private Long id;

  /** 父id */
  private Long parent;

  /** 名称 */
  private String name;

  /** 顺序 */
  private Integer sort;
}
