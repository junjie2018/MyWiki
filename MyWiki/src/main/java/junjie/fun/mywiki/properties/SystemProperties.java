package junjie.fun.mywiki.properties;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "system")
public class SystemProperties {

    private RunMode runMode;

    public enum RunMode {
        /**
         * 开发环境
         */
        DEV,
        /**
         * 生产环境
         */
        PROD;
    }

    @Component
    @ConfigurationPropertiesBinding
    public static class RunModeConverter implements Converter<String, RunMode> {
        @Override
        public RunMode convert(String source) {
            return StringUtils.equalsIgnoreCase(source, "prod") ? RunMode.PROD : RunMode.DEV;
        }
    }
}
