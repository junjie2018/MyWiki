package junjie.fun.mywiki.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import junjie.fun.mywiki.context.UserContext;
import junjie.fun.mywiki.exception.BusinessException;
import junjie.fun.mywiki.request.PageRequest;
import junjie.fun.mywiki.request.condition.PageUserCondition;
import junjie.fun.mywiki.request.user_admin.CreateUserRequest;
import junjie.fun.mywiki.request.user_admin.UpdateUserRequest;
import junjie.fun.mywiki.response.ResponseVo;
import junjie.fun.mywiki.response.data.UserData;
import junjie.fun.mywiki.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static junjie.fun.mywiki.constant.code.BusinessCode.CANT_NOT_DELETE_SELF;

/**
 * 用户管理（仅限具有管理权限的成员访问）
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class UserAdminController {

    private final UserService userService;

    /**
     * 创建一个用户
     */
    @PostMapping("/user/createUser")
    public ResponseVo<Long> createUser(@Valid @RequestBody CreateUserRequest request) {
        return ResponseVo.success(userService.createUser(request));
    }

    /**
     * 更新一个用户
     */
    @PostMapping("/user/updateUser")
    public ResponseVo<Long> updateUser(@Valid @RequestBody UpdateUserRequest request) {

        userService.updateUser(request);

        return ResponseVo.success();
    }


    /**
     * 删除一个用户
     *
     * @param userId 用户Id
     */
    @PostMapping("/user/deleteUser")
    public ResponseVo<Long> deleteUser(@RequestParam("userId") Long userId) {

        // 检查用户是否正在删除自己
        if (userId.equals(UserContext.getUserId())) {
            throw new BusinessException(CANT_NOT_DELETE_SELF);
        }

        userService.removeById(userId);

        return ResponseVo.success(userId);
    }

    /**
     * 分页查询用户信息
     */
    @PostMapping("/user/pageUser")
    public ResponseVo<Page<UserData>> pageUser(PageRequest<PageUserCondition> request) {
        return ResponseVo.success(userService.pageUser(request));
    }
}
