package ru.netology.conditionalapplicationjclo11.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.netology.conditionalapplicationjclo11.systemProfile.DevProfile;
import ru.netology.conditionalapplicationjclo11.systemProfile.ProductionProfile;
import ru.netology.conditionalapplicationjclo11.systemProfile.SystemProfile;

@Configuration
public class JavaConfig {

    @Bean(name = "devProfile")
    @ConditionalOnProperty(prefix = "netology", name = "profile", havingValue = "true", matchIfMissing = true)
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @Bean(name = "prodProfile")
    @ConditionalOnProperty(prefix = "netology", name = "profile", havingValue = "false")
    public SystemProfile prodProfile() {
        return new ProductionProfile();
    }
}
