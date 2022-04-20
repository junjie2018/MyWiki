package junjie.fun.mywiki.request;

import junjie.fun.mywiki.common.request.PageRequest;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
public class PageUserRequest extends PageRequest {

  /** 分页条件 */
  private Condition condition = new Condition();

  @Data
  public static class Condition {}
}
