package junjie.fun.mywiki.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class UpdateContentRequest {

  /** 文档id */
  @NotNull private Long id;

  /** 内容 */
  private String content;
}
