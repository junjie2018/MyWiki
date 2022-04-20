package junjie.fun.mywiki.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import junjie.fun.mywiki.common.response.PageData;
import junjie.fun.mywiki.common.response.ResponseVo;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

import junjie.fun.mywiki.request.*;
import junjie.fun.mywiki.response.*;
import junjie.fun.mywiki.service.*;

/** 用户管理 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  /** 分页查询User */
  @PostMapping("/user/pageUser")
  public ResponseVo<PageData<UserData>> pageUser(@Valid @RequestBody PageUserRequest request) {
    return ResponseVo.success(userService.pageUser(request));
  }

  /** 创建User */
  @PostMapping("/user/createUser")
  public ResponseVo<Long> createUser(@Valid @RequestBody CreateUserRequest request) {
    return ResponseVo.success(userService.createUser(request));
  }

  /** 更新User */
  @PostMapping("/user/updateUser")
  public ResponseVo<Long> updateUser(@Valid @RequestBody UpdateUserRequest request) {
    return ResponseVo.success(userService.updateUser(request));
  }

  /**
   * 根据Id查询User
   *
   * @param userId User的主键Id
   */
  @PostMapping("/user/queryUser")
  public ResponseVo<UserData> queryUser(@RequestParam("userId") Long userId) {

    List<UserData> userData = userService.queryUsers(Collections.singletonList(userId));

    return ResponseVo.success(CollectionUtils.isNotEmpty(userData) ? userData.get(0) : null);
  }

  /**
   * 根据Id数组批量查询User
   *
   * @param userIds User的主键Id数组
   */
  @PostMapping("/user/queryUsers")
  public ResponseVo<List<UserData>> queryUsers(@RequestBody List<Long> userIds) {

    return ResponseVo.success(userService.queryUsers(userIds));
  }

  /**
   * 根据Id删除User
   *
   * @param userId User的主键Id
   */
  @PostMapping("/user/deleteUser")
  public ResponseVo<Long> deleteUser(@RequestParam("userId") Long userId) {
    userService.deleteUsers(Collections.singletonList(userId));

    return ResponseVo.success();
  }

  /**
   * 根据Id数组批量删除User
   *
   * @param userIds User的主键Id数组
   */
  @PostMapping("/user/deleteUsers")
  public ResponseVo<Void> deleteUsers(@RequestBody List<Long> userIds) {

    userService.deleteUsers(userIds);

    return ResponseVo.success();
  }
}
