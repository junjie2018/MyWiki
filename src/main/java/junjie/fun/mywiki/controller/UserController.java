package junjie.fun.mywiki.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

import java.util.Random;

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

        int[] a = new int[1];

        return ResponseVo.success();
    }

    public static void main(String[] args) {
        String tmp = "insert into `ebook` (id, name, description, cover, category1_id, category2_id) values (%d, '%s', '%s', '/images/cover1.png', '%s', '%s');\n";


        int[] category1Ids = new int[]{1, 4, 7, 10, 12};
        int[] category2Ids = new int[]{2, 3, 5, 6, 8, 9, 11, 13, 14, 15};


        for (int i = 0; i < 200; i++) {
            System.out.printf(tmp,
                    i + 1,
                    "Name_" + (i + 1),
                    "Desc_" + (i + 1),
                    category1Ids[new Random().nextInt(category1Ids.length)],
                    category2Ids[new Random().nextInt(category2Ids.length)]);
        }
    }
}
