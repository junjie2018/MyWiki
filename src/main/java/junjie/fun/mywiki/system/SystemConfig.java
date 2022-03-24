package junjie.fun.mywiki.system;

import junjie.fun.mywiki.properties.SystemProperties;
import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SystemConfig implements ApplicationContextAware {

    private static SystemProperties systemProperties;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        systemProperties = applicationContext.getBean(SystemProperties.class);
    }

    public static boolean isNotProd() {
        return !SystemProperties.RunMode.PROD.equals(systemProperties.getRunMode());
    }

    public static boolean isProd() {
        return SystemProperties.RunMode.PROD.equals(systemProperties.getRunMode());
    }
}
