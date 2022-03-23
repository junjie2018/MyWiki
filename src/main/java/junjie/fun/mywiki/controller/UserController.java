package junjie.fun.mywiki.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import junjie.fun.mywiki.entity.User;
import junjie.fun.mywiki.request.PageRequest;
import junjie.fun.mywiki.request.condition.PageUserCondition;
import junjie.fun.mywiki.request.user.CreateOrUpdateUserRequest;
import junjie.fun.mywiki.request.user.LoginOutRequest;
import junjie.fun.mywiki.request.user.LoginRequest;
import junjie.fun.mywiki.request.user.ResetPasswordRequest;
import junjie.fun.mywiki.response.ResponseVo;
import junjie.fun.mywiki.response.data.LoginData;
import junjie.fun.mywiki.response.data.UserData;
import junjie.fun.mywiki.service.UserService;
import junjie.fun.mywiki.utils.SnowFlake;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 用户管理
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final SnowFlake snowFlake;
    private final UserService userService;
    private final StringRedisTemplate stringRedisTemplate;

    @PostMapping("/user/createOrUpdateUser")
    public ResponseVo<Long> createOrUpdateUser(CreateOrUpdateUserRequest request) {
        return ResponseVo.success(userService.createOrUpdateUser(request));
    }

    @PostMapping("/user/login")
    public ResponseVo<LoginData> login(@Valid @RequestBody LoginRequest request) {

        LoginData loginData = userService.login(request);

        String token = String.valueOf(snowFlake.nextId());

        String redisKey = String.format("token:%s", token);
        loginData.setToken(token);

        stringRedisTemplate.opsForValue().set(redisKey, JSON.toJSONString(loginData));

        return ResponseVo.success(loginData);
    }

    @PostMapping("/user/loginOut")
    public ResponseVo<Void> loginOut(@Valid @RequestBody LoginOutRequest request) {
        String redisKey = String.format("token:%s", request.getToken());
        stringRedisTemplate.delete(redisKey);
        return ResponseVo.success();
    }

    @PostMapping("/user/deleteUser")
    public ResponseVo<Long> deleteUser(@RequestParam("userId") Long userId) {

        userService.removeById(userId);

        return ResponseVo.success(userId);
    }

    @PostMapping("/user/resetPassword")
    public ResponseVo<Long> resetPassword(ResetPasswordRequest request) {
        return ResponseVo.success(userService.resetPassword(request));
    }

    @PostMapping("/user/pageUser")
    public ResponseVo<Page<UserData>> pageUser(PageRequest<PageUserCondition> request) {
        return ResponseVo.success(userService.pageUser(request));
    }
}
