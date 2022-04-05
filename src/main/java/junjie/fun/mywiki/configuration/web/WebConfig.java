package junjie.fun.mywiki.configuration.web;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
//import junjie.fun.mywiki.interceptor.LoginInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Locale;

/**
 * 对Spring MVC进行配置：
 * 1.配置HttpMessageConverts
 * 2.配置针对LocalDateTime的转换器
 */
@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final FastJsonHttpMessageConverter fastJsonHttpMessageConverter;
//    private final LoginInterceptor loginInterceptor;

    /**
     * 配置HttpMessageConverts
     *
     * @param converters 当前SpringBoot实例已配置的HttpMessageConverts
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 这部分代码还需要斟酌，需要确认下会不会影响到文件上传
        converters.clear();

        converters.add(fastJsonHttpMessageConverter);
    }

    /**
     * 配置针对LocalDateTime的转换器
     *
     * @param registry formatter注册中心
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new Formatter<LocalDateTime>() {
            @Override
            public String print(LocalDateTime localDateTime, Locale locale) {

                ZoneId systemDefaultZoneId = ZoneId.systemDefault();
                ZoneOffset offset = systemDefaultZoneId.getRules().getOffset(localDateTime);

                return String.valueOf(localDateTime.toInstant(offset).toEpochMilli());
            }

            @Override
            public LocalDateTime parse(String text, Locale locale) throws ParseException {

                long timestamp = Long.parseLong(text);

                Instant instant = Instant.ofEpochMilli(timestamp);
                ZoneId zone = ZoneId.systemDefault();

                return LocalDateTime.ofInstant(instant, zone);
            }
        });

        WebMvcConfigurer.super.addFormatters(registry);
    }

//    /**
//     * 配置拦截器，符合要求的url进行拦截
//     *
//     * @param registry interceptors注册中心
//     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//
//        registry.addInterceptor(loginInterceptor)
//                .addPathPatterns("/**")
//                .excludePathPatterns("/user/login");
//
//        WebMvcConfigurer.super.addInterceptors(registry);
//    }

    /**
     * CORS配置
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedHeaders(CorsConfiguration.ALL)
                .allowedMethods(CorsConfiguration.ALL)
                .allowCredentials(true)
                // 1小时内不需要再预检（发Options请求）
                .maxAge(3600);
    }
}
