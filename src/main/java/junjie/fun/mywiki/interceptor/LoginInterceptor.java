package junjie.fun.mywiki.interceptor;

import com.alibaba.fastjson.JSON;
import junjie.fun.mywiki.context.UserContext;
import junjie.fun.mywiki.dto.TokenDataDTO;
import junjie.fun.mywiki.system.SystemConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static junjie.fun.mywiki.constant.SystemConstant.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoginInterceptor implements HandlerInterceptor {
    private final StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (SystemConfig.isNotProd()) {
            request.setAttribute(REQUEST_START_TIME, System.currentTimeMillis());
        }

        // OPTIONS请求不做校验（前后端分离架构中，前端会发一个OPTIONS请求做预检，对预检查不做校验）
        if (StringUtils.equalsIgnoreCase(OPTIONS, request.getMethod())) {
            return true;
        }

        log.info("{}：URL被拦截", request.getRequestURL().toString());

        // 校验Token
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            log.info("{}：Token为空，请求不通过", request.getRequestURL().toString());
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }

        String tokenValue = stringRedisTemplate.opsForValue().get(getTokenKey(token));
        if (StringUtils.isBlank(tokenValue)) {
            log.info("{}：Token无效，请求不通过", request.getRequestURL().toString());
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        } else {
            log.info("{}：Token校验成功", request.getRequestURL().toString());
            TokenDataDTO tokenDataDTO = JSON.parseObject(tokenValue, TokenDataDTO.class);
            UserContext.setTokenDataDTO(tokenDataDTO);
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        if (SystemConfig.isNotProd()) {
            Long startTime = (Long) request.getAttribute(REQUEST_START_TIME);
            log.info("Request {} Time Use: {}",
                    request.getRequestURL().toString(),
                    System.currentTimeMillis() - startTime);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
