package net.mooko.moosj.config;

import net.mooko.common.holder.JsonConverterHolder;
import net.mooko.common.json.Converter;
import net.mooko.moosj.Application;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author puras <he@puras.me>
 * @since 15/8/24  上午10:27
 */
@Configuration
@ComponentScan(basePackageClasses = Application.class, excludeFilters = @ComponentScan.Filter({Controller.class, RestController.class, Configuration.class}))
public class ApplicationConfig {

    @Bean
    public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
        PropertyPlaceholderConfigurer config = new PropertyPlaceholderConfigurer();
        config.setLocation(new ClassPathResource("/config.properties"));
        return config;
    }

    @Bean
    public MessageSource resourceBundleMessageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("/i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public static PathConfig pathConfig() {
        return PathConfig.INSTANCE;
    }

    @Bean
    public Converter jsonConverter() {
        return JsonConverterHolder.getInstance().getConverter();
    }
}
