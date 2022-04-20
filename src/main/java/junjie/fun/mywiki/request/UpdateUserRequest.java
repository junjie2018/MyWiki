package junjie.fun.mywiki.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class UpdateUserRequest {

  /** ID */
  @NotNull private Long id;

  /** 登陆名 */
  private String loginName;

  /** 昵称 */
  private String name;

  /** 密码 */
  private String password;
}
