package com.creativesemester.SejongCodingMate.global.security;

import com.creativesemester.SejongCodingMate.global.jwt.JwtAuthFilter;
import com.creativesemester.SejongCodingMate.global.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    // 1. JWT 를 사용하기 위해서 JwtUtil DI
    private final JwtUtil jwtUtil;


    // 2. CORS 설정
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.addExposedHeader("Authorization");
        config.setAllowCredentials(true);
        config.validateAllowCredentials();
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    // 3. BCrypt 를 통한 암호화를 진행.
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // 4. 아래의 securityFilterChain 보다 먼저 걸리는 설정.
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web) -> web.ignoring()
//                .requestMatchers(PathRequest.toH2Console()) //,,, h2 를 사용한다면 Resource 접근 허용 설정
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    // 5. Spring SecurityFilterChain 설정
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http.cors();
        http.csrf().disable();                  // 사이트간 요청 위조를 거부.
        http.formLogin().disable();             // FormLogin 사용하지 않음으로 거부.

        // 기본 설정인 Session 방식은 사용하지 않고 JWT 방식을 사용하기 위한 설정
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Security 에서 허용해줄 부분
        http.authorizeRequests()
                .antMatchers("/api/member/**").permitAll()
                .anyRequest().authenticated()

                // Filter 단에서 생기는 오류를 잡기 위한 EntryPoint 생성
                .and().exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                // JWT 인증/인가를 사용하기 위한 설정
                .and().addFilterBefore(new JwtAuthFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
