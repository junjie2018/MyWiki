package junjie.fun.mywiki.request;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CreateUserRequest {

  /** 登陆名 */
  private String loginName;

  /** 昵称 */
  private String name;

  /** 密码 */
  private String password;
}
