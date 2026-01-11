package jp.co.sss.cytech.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            // 認可設定（URL制御）
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/login",
                    "/user/register",
                    "/css/**",
                    "/js/**",
                    "/images/**"
                ).permitAll()   // 未ログインでもOK
                .anyRequest().authenticated() // それ以外はログイン必須
            )

            // ログイン設定
            .formLogin(login -> login
                .loginPage("/login")        // 自作ログイン画面
                .usernameParameter("username")
                .loginProcessingUrl("/login") // POST先（form action）
                .defaultSuccessUrl("/top", true)
                .permitAll()
            )

            // ログアウト設定
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            );

        return http.build();
    }
    
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
