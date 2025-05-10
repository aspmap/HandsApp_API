package run.itlife.handsapp_api.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.cors.CorsConfiguration;

import java.util.LinkedHashMap;
import java.util.Map;

@ConfigurationProperties(prefix = "spring.cors.url")
public class UrlBasedCorsConfigurationProperties {

    private Map<String, CorsConfiguration> configurations = new LinkedHashMap<>();

    public Map<String, CorsConfiguration> getConfigurations() {
        return this.configurations;
    }

    public void setConfigurations(LinkedHashMap<String, CorsConfiguration> configurations) {
        this.configurations = configurations;
    }
}
