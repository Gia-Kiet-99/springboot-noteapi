package com.example.noteapi.config;

import com.example.noteapi.security.JwtAuthenticationFilter;
import com.example.noteapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  private final UserService userService;

  @Autowired
  public WebSecurityConfig(UserService userService) {
    this.userService = userService;
  }

  @Bean
  public JwtAuthenticationFilter jwtAuthenticationFilter() {
    return new JwtAuthenticationFilter();
  }

  @Bean(BeanIds.AUTHENTICATION_MANAGER)
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    // Get AuthenticationManager bean
    return super.authenticationManagerBean();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    // Password encoder, để Spring Security sử dụng mã hóa mật khẩu người dùng
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userService) // Cung cáp userservice cho spring security
        .passwordEncoder(passwordEncoder()); // cung cấp password encoder
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .cors()
        .and()
        .csrf()
          .disable()
        .authorizeRequests()
        // Cho phép tất cả mọi người truy cập vào 2 địa chỉ này
          .antMatchers("/auth/signup", "/auth/login")
            .permitAll()
        // Tất cả các request khác đều cần phải xác thực mới được truy cập
          .anyRequest()
            .authenticated()
        .and()
        // stateless session, ko sử dụng session để lưu thông tin user
        .sessionManagement()
          .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    // Thêm một lớp Filter kiểm tra jwt
    http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
  }
}
