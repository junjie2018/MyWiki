package junjie.fun.mywiki.request;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CreateContentRequest {

  /** 内容 */
  private String content;
}
