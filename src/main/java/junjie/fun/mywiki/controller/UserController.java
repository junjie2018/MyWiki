package junjie.fun.mywiki.controller;

import com.alibaba.fastjson.JSON;
import junjie.fun.mywiki.context.UserContext;
import junjie.fun.mywiki.dto.TokenDataDTO;
import junjie.fun.mywiki.request.user.LoginRequest;
import junjie.fun.mywiki.request.user.ChangePasswordRequest;
import junjie.fun.mywiki.response.ResponseVo;
import junjie.fun.mywiki.response.data.LoginData;
import junjie.fun.mywiki.service.UserService;
import junjie.fun.mywiki.utils.CopyUtils;
import junjie.fun.mywiki.utils.SnowFlake;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static junjie.fun.mywiki.constant.SystemConstant.getTokenKey;

/**
 * 用户基本操作
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final SnowFlake snowFlake;
    private final UserService userService;
    private final StringRedisTemplate stringRedisTemplate;

    /**
     * 用户登录接口
     */
    @PostMapping("/user/login")
    public ResponseVo<LoginData> login(@Valid @RequestBody LoginRequest request) {

        LoginData loginData = userService.login(request);

        String token = String.valueOf(snowFlake.nextId());

        String redisKey = String.format("token:%s", token);
        loginData.setToken(token);

        stringRedisTemplate.opsForValue().set(redisKey, JSON.toJSONString(CopyUtils.copy(loginData, TokenDataDTO.class)));

        return ResponseVo.success(loginData);
    }

    /**
     * 用户登出接口
     */
    @PostMapping("/user/loginOut")
    public ResponseVo<Void> loginOut() {
        stringRedisTemplate.delete(getTokenKey(UserContext.getToken()));
        return ResponseVo.success();
    }

    /**
     * 用户重置自己密码接口
     */
    @PostMapping("/user/changePassword")
    public ResponseVo<Void> changePassword(@Valid @RequestBody ChangePasswordRequest request) {

        userService.changePassword(request);

        return ResponseVo.success();
    }
}
