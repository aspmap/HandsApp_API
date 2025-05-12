package run.itlife.handsapp_api.config;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
@ComponentScan("run.itlife.handsapp_api")
public class RestConfig {
    @Value("${jwt.public.key}")
    RSAPublicKey key;
    @Value("${jwt.private.key}")
    RSAPrivateKey priv;

    @Autowired
    private MyBasicAuthenticationEntryPoint authEndpoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(c -> c.configurationSource(request -> {
                    CorsConfiguration cors = new CorsConfiguration();
                    cors.setAllowedHeaders(Arrays.asList("Access-Control-Allow-Origin"));
                    //cors.setAllowedOrigins(Arrays.asList("http://localhost:3000/refresh", "http://localhost:3000/token", "http://localhost:3000", "http://localhost:3000/"));
                    cors.setAllowedOrigins(Arrays.asList("http://localhost:8081/refresh", "http://localhost:8081/token", "http://localhost:8081", "http://localhost:8081/"));
                    cors.setAllowedHeaders(Arrays.asList("*"));
                    cors.setAllowedMethods(Arrays.asList("*"));
                    cors.setAllowCredentials(true);
                    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                    source.registerCorsConfiguration("/**", cors);
                    return cors;
                }))
                .authorizeHttpRequests()
                .and()
                .authorizeHttpRequests((authorize) -> authorize
                        .anyRequest().authenticated()
                )
                .csrf((csrf) -> csrf.ignoringRequestMatchers("/token"))
                .httpBasic(Customizer.withDefaults())
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling((exceptions) -> exceptions
                        .authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint())
                        .accessDeniedHandler(new BearerTokenAccessDeniedHandler())


                );
        return http.build();
    }

 /*   @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(c -> c.configurationSource(request -> {
                    CorsConfiguration cors = new CorsConfiguration();
                    cors.setAllowedHeaders(Arrays.asList("Access-Control-Allow-Origin"));
                    cors.setAllowedOrigins(Arrays.asList("http://localhost:3000/refresh", "http://localhost:3000/token", "http://localhost:3000", "http://localhost:3000/"));
                    cors.setAllowedHeaders(Arrays.asList("*"));
                    cors.setAllowedMethods(Arrays.asList("*"));
                    cors.setAllowCredentials(true);
                    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                    source.registerCorsConfiguration("/**", cors);
                    return cors;
                }))
                .authorizeHttpRequests()
                .and()
                .authorizeHttpRequests((authorize) -> authorize
                        .anyRequest().authenticated()
                )
                .csrf((csrf) -> csrf.ignoringRequestMatchers("/token"))
                .httpBasic(Customizer.withDefaults())
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling((exceptions) -> exceptions
                        .authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint())
                        .accessDeniedHandler(new BearerTokenAccessDeniedHandler())


                )
*//*                .formLogin()
                .loginPage("/token")
               // .usernameParameter("username")
               // .passwordParameter("password")
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .logoutSuccessUrl("/")*//*
                .httpBasic().authenticationEntryPoint(authEndpoint);
        return http.build();
    }*/

 /*   @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                //.antMatchers("/login", "/resources/css/**", "/resources/js/**", "/resources/img/icons/**", "/resources/img/login/**")
                //.permitAll()
                //.antMatchers("/resources/img/users/**", "/resources/video/**")
                //.authenticated()
               // .and()
               // .oauth2Login()
                //.defaultSuccessUrl("/main", true)
               // .loginPage("/login")
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .logoutSuccessUrl("/");
        return http.build();

    }*/

/*    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(c -> c.configurationSource(request -> {
                    CorsConfiguration cors = new CorsConfiguration();
                    cors.setAllowedHeaders(Arrays.asList("Access-Control-Allow-Origin"));
                    cors.setAllowedOrigins(Arrays.asList("http://localhost:3000/refresh", "http://localhost:3000/token", "http://localhost:3000", "http://localhost:3000/"));
                    cors.setAllowedHeaders(Arrays.asList("*"));
                    cors.setAllowedMethods(Arrays.asList("*"));
                    cors.setAllowCredentials(true);
                    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                    source.registerCorsConfiguration("/**", cors);
                    return cors;
                }))
                .csrf()
                .disable()
                .authorizeRequests()
                .and()
                .formLogin()
                .loginPage("/token") // на адрес / необходимо добавить контроллер, который выдает шаблон
                .loginProcessingUrl("/token") // адрес конечной точки, куда по методу POST отправляются имя и пароль при нажатии кнопки входа
                .defaultSuccessUrl("/token") // адрес страницы, куда пользователь перенаправляется при успешном входе
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .logoutSuccessUrl("/");
        return http.build();
    }*/



 /*   @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(c -> c.configurationSource(request -> {
                    CorsConfiguration cors = new CorsConfiguration();
                    cors.setAllowedHeaders(Arrays.asList("Access-Control-Allow-Origin"));
                    cors.setAllowedOrigins(Arrays.asList("http://localhost:3000/refresh", "http://localhost:3000/token", "http://localhost:3000", "http://localhost:3000/"));
                    cors.setAllowedHeaders(Arrays.asList("*"));
                    cors.setAllowedMethods(Arrays.asList("*"));
                    cors.setAllowCredentials(true);
                    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                    source.registerCorsConfiguration("/**", cors);
                    return cors;
                }))
                .authorizeHttpRequests()
                .and()
                .authorizeHttpRequests((authorize) -> authorize
                        .anyRequest().authenticated()
                )
                .csrf((csrf) -> csrf.ignoringRequestMatchers("/token"))
                .httpBasic(Customizer.withDefaults())
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling((exceptions) -> exceptions
                        .authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint())
                        .accessDeniedHandler(new BearerTokenAccessDeniedHandler())


                );
        return http.build();
    }*/

    @Bean
    JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(this.key).build();
    }

    @Bean
    JwtEncoder jwtEncoder() {
        JWK jwk = new RSAKey.Builder(this.key).privateKey(this.priv).build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }

    @Bean
    //Когда придёт запрос нужно использовать UserService для того, чтобы проверять права пользователя, логин и пароль,
    //проводить аутентификацию и выдавать им права
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(); // хеширует в md5
    }
}
