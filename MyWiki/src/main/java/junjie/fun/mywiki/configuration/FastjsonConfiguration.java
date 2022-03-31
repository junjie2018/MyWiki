package junjie.fun.mywiki.configuration;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import javax.annotation.PostConstruct;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Arrays;


/**
 * 对Fastjson进行全局配置，使用其可以自动将LocalDateTime类型转换成Number类型时间戳
 */
@Configuration
public class FastjsonConfiguration {
    @PostConstruct
    public void configFastjson() {
        SerializeConfig serializeConfig = SerializeConfig.globalInstance;

        // 添加处理LocalDateTime的处理器
        serializeConfig.put(LocalDateTime.class, (JSONSerializer serializer,
                                                  Object object,
                                                  Object fieldName,
                                                  Type fieldType,
                                                  int features) -> {

            LocalDateTime fieldValue = (LocalDateTime) object;

            // 针对1.2.79进行的调整
            if (object == null) {
                serializer.writeNull();
                return;
            }

            ZoneId systemDefaultZoneId = ZoneId.systemDefault();
            ZoneOffset offset = systemDefaultZoneId.getRules().getOffset(fieldValue);

            serializer.write(fieldValue.toInstant(offset).toEpochMilli());
        });
    }

    @Bean
    public FastJsonHttpMessageConverter fastJsonHttpMessageConverter() {
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();

        // 配置FastJsonConfig
        FastJsonConfig config = new FastJsonConfig();

        config.setSerializerFeatures(
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteDateUseDateFormat,
                SerializerFeature.DisableCircularReferenceDetect);

        fastJsonHttpMessageConverter.setFastJsonConfig(config);

        fastJsonHttpMessageConverter.setSupportedMediaTypes(Arrays.asList(
                MediaType.APPLICATION_JSON,
                MediaType.ALL));

        return fastJsonHttpMessageConverter;
    }
}
