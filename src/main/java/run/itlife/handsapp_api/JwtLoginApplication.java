package run.itlife.handsapp_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import run.itlife.handsapp_api.config.UrlBasedCorsConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(UrlBasedCorsConfigurationProperties.class)
public class JwtLoginApplication {
    public static void main(String[] args) {
        SpringApplication.run(JwtLoginApplication.class, args);
    }
}