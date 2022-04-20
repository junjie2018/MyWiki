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
@TableName("t_user")
public class User extends BaseEntity {

  /** 登陆名 */
  private String loginName;

  /** 昵称 */
  private String name;

  /** 密码 */
  private String password;
}
