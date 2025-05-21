package run.itlife.handsapp_api.config;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

//@Configuration
public class CORSAdvice {

/*    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.setAllowedHeaders(Arrays.asList("Access-Control-Allow-Origin"));
        config.setAllowedOrigins(Arrays.asList("https://localhost:8443/refresh", "https://localhost:8443/token", "https://localhost:8443", "https://localhost:8443/", "https://localhost:8443/posts_ms"));
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter((source)));
        bean.setOrder(0);
        return bean;
    }*/

}
