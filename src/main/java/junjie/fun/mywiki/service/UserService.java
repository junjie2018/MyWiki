package junjie.fun.mywiki.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import junjie.fun.mywiki.common.response.PageData;

import java.util.List;

import junjie.fun.mywiki.request.*;
import junjie.fun.mywiki.response.*;
import junjie.fun.mywiki.entity.*;

@Service
public interface UserService extends IService<User> {

  /** 创建用户 */
  Long createUser(CreateUserRequest request);

  /** 删除用户 */
  void deleteUsers(List<Long> userIds);

  /** 编辑用户 */
  Long updateUser(UpdateUserRequest request);

  /** 分页查找用户 */
  PageData<UserData> pageUser(PageUserRequest request);

  /** 根据Id数组查找用户 */
  List<UserData> queryUsers(List<Long> userIds);
}
