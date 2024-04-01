package com.projectif.ooslibrary.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectif.ooslibrary.member.domain.Role;
import com.projectif.ooslibrary.member.repository.MemberRepository;
import com.projectif.ooslibrary.security.CustomUserDetailsService;
import com.projectif.ooslibrary.security.oauth.OAuth2SuccessHandler;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import java.io.PrintWriter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final MemberRepository memberRepository;

    private final AuthenticationEntryPoint authenticationEntryPoint =
            (request, response, authException) -> {
                ErrorResponse fail = new ErrorResponse(HttpStatus.UNAUTHORIZED, "Spring security 인증 실패!!!");
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                String json = new ObjectMapper().writeValueAsString(fail);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.setCharacterEncoding("UTF-8");
                PrintWriter writer = response.getWriter();
                writer.write(json);
                writer.flush();
            };

    private final AccessDeniedHandler accessDeniedHandler =
            (request, response, accessDeniedException) -> {
                ErrorResponse fail = new ErrorResponse(HttpStatus.FORBIDDEN, "Spring security 인가 실패!!!");
                response.setStatus(HttpStatus.FORBIDDEN.value());
                String json = new ObjectMapper().writeValueAsString(fail);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.setCharacterEncoding("UTF-8");
                PrintWriter writer = response.getWriter();
                writer.write(json);
                writer.flush();
            };
    /**
     * AuthenticationEntryPoint : 인증 예외처리, 401(Unauthorized) 상태 코드 전달
     * AccessDeniedHandler : 권한(인가) 예외처리, 403(Forbidden) 상태 코드 전달
     */

    private final OAuth2UserService oAuth2UserService;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;
    /**
     * OAuth2UserService - OAuth2 인증 처리 서비스
     * OAuth2SuccessHandler - OAuth2 인증이 성공했을 시 처리하는 핸들러 설정
     */


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                // HTTPS를 강제하도록 변경
//                .requiresChannel(channelRequestMatcherRegistry ->
//                        channelRequestMatcherRegistry.anyRequest().requiresSecure())
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .headers((headerConfig) ->
                        headerConfig.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)
                )
                .formLogin(form -> form
                                .loginPage("/login") // GET
//                                .loginProcessingUrl("/login") // POST
                                .successForwardUrl("/login_success") // POST
                                .permitAll()
                )
                .oauth2Login(oauth2 -> {
                    oauth2.userInfoEndpoint(userInfo -> userInfo.userService(oAuth2UserService));
                    oauth2.successHandler(oAuth2SuccessHandler);
                })
                .logout(logoutConfigurer -> {
                    logoutConfigurer.logoutSuccessUrl("/");
                    logoutConfigurer.invalidateHttpSession(true); // 세션 무효화 설정
                    logoutConfigurer.deleteCookies("JSESSIONID");
                })
                .authorizeHttpRequests((authorizeRequests) ->
                        authorizeRequests
                                .requestMatchers(PathRequest.toH2Console()).permitAll()
                                .requestMatchers("/", "/oauth2/authorization/naver", "/login").permitAll()
                                .requestMatchers("/user/**").hasRole(Role.USER.name())
                                .requestMatchers("/admin/**", "/api/v1/**").hasRole(Role.ADMIN.name())
                                .anyRequest().permitAll()
                )
                // 세션 관리 기능
                .sessionManagement((sessionManagement) ->
                        sessionManagement
                                // 세션 동시성 관련 기능
                                .sessionConcurrency((sessionConcurrency) ->
                                        sessionConcurrency
                                                //  최대 허용 가능 세션 수를 정할 수 있다. 만약 -1을 넣는다면 무제한 로그인 세션을 허용한다는 의미
                                                .maximumSessions(1)
                                                // 최초 로그인 후, 로그아웃 하고 다시 로그인이 안되는 현상 원인
                                                // 기본값은 false(기존 세션 만료), true : 사용자의 인증이 실패한다(동시 로그인 차단)
                                                .maxSessionsPreventsLogin(true)
                                                .expiredUrl("/login?expired")
                                                .sessionRegistry(sessionRegistry())
                                )
                )
                .exceptionHandling((exceptionConfig) ->
                        exceptionConfig
                                .authenticationEntryPoint(authenticationEntryPoint) // 인증 예외 - 401
                                .accessDeniedHandler(accessDeniedHandler) // 인가 예외 - 403
                ); // 401 403 관련 예외처리

        return http.build();
    }
    // 스프링 시큐리티는 ExceptionHandling 을 통해 인증, 인가 과정에서 발생한 예외를 처리할 수 있습니다.


    // Custom UserDetailsService 등록
    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService(memberRepository);
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    // Register HttpSessionEventPublisher
    @Bean
    public static ServletListenerRegistrationBean httpSessionEventPublisher() {
        return new ServletListenerRegistrationBean(new HttpSessionEventPublisher());
    }


//    @Bean
//    public PasswordEncoder passwordEncoder() {
//
//        return new BCryptPasswordEncoder();
//    }


    @Getter
    @RequiredArgsConstructor
    public class ErrorResponse {

        private final HttpStatus status;
        private final String message;
    }

}
