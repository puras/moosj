package net.mooko.moosj.config;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author puras <he@puras.me>
 * @since 15/8/24  上午10:27
 */
@Configuration
public class CaptchaConfig {

    @Bean
    public Producer captchaProducer() {
        DefaultKaptcha kaptcha = new DefaultKaptcha();
        Properties prop = new Properties();
        prop.put("kaptcha.image.width", 68);
        prop.put("kaptcha.image.height", 28);
        Config config = new Config(prop);
        kaptcha.setConfig(config);
        return kaptcha;
    }
}
