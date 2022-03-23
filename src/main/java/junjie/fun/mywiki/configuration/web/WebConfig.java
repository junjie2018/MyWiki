package junjie.fun.mywiki.configuration.web;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
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
}
