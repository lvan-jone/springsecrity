//package com.example.springsecurity.config;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//// 自定义认证过滤器
//public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        String username = obtainUsername(request);
//        String password = obtainPassword(request);
//
//        // 检查用户名和密码是否匹配静态变量中的值
//        if (USERNAME.equals(username) && PASSWORD.equals(password)) {
//            // 创建一个已认证的用户对象
//            User userDetails = User.withDefaultPasswordEncoder()
//                    .username(USERNAME)
//                    .password(PASSWORD)
//                    .roles("USER")
//                    .build();
//
//            // 返回一个成功的认证结果
//            return new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(
//                    userDetails, null, userDetails.getAuthorities());
//        } else {
//            throw new BadCredentialsException("Invalid username or password");
//        }
//    }
//}