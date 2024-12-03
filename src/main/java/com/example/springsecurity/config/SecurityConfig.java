package com.example.springsecurity.config;

import com.example.springsecurity.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.web.SecurityFilterChain;
@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserService userService;

    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/login", "/").permitAll()  // 允许未认证用户访问登录页面和主页
                        .anyRequest().authenticated()  // 其他所有请求需要认证
                )
                .formLogin((form) -> form
                        .loginPage("/login")  // 自定义登录页面
                        .defaultSuccessUrl("/", false)  // true登录成功后跳转到主页,false重定向原来url
                        .permitAll()
                )
                .logout(LogoutConfigurer::permitAll);// 允许未认证用户访问登出页面;

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        log.info("PasswordEncoder" );
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        log.info("configureGlobal:{}",auth );
        auth.userDetailsService(userService);
    }
}