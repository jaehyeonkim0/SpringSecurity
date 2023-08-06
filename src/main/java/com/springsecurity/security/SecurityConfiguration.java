package com.springsecurity.security;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfiguration {

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()                    //HTTP 요청에 대한 보안 규칙을 설정하기 위한 시작점
//                .antMatchers("/").permitAll()         //antMatchers("/") 루트 경로('/')에 대한 접근 권한을 설정, .permitAll() 루트 경로에 대해 모든 사용자에게 접근을 허용
//                .anyRequest().authenticated()           //루트 경로를 제외한 다른 모든 요청에 대해서는 인증이 필요하다는 것을 설정
//                .and()
//        .formLogin()                                    //폼 기반 로그인을 사용하도록 설정
//                .loginPage("/login")                    //사용자 정의 로그인 페이지의 URL을 설정합니다. /login 경로는 사용자 정의 로그인 페이지를 생성하는 데 사용
//                .permitAll()
//                .and()
//         .logout()                                      //로그아웃 처리를 설정
//                .permitAll();
//
//        return http.build();
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username("user")
//                        .password("password")
//                        .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(user); // 메모리에 사용자 정보를 담는다.
//    }

        private final UserDetailsService userDetailsService;    //UserDetailsService 란? Spring Security에서 유저의 정보를 가져오는 인터페이스


        @Bean
        public static BCryptPasswordEncoder bCryptPasswordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            /* @formatter:off */
            http
                    .authorizeRequests()
                    .antMatchers("/", "/signup").permitAll() // 설정한 리소스의 접근을 인증절차 없이 허용
                    .anyRequest().authenticated()   // 그 외 모든 리소스를 의미하며 인증 필요
                    .and()
            .formLogin()                            //폼 기반 로그인을 사용하도록 설정
                    .permitAll()
                    .loginPage("/login")            // 기본 로그인 페이지, 사용자 정의 로그인 페이지의 URL을 설정합니다. /login 경로는 사용자 정의 로그인 페이지를 생성하는 데 사용
                    .and()
            .logout()
                    .permitAll()
                    // .logoutUrl("/logout") // 로그아웃 URL (기본 값 : /logout)
                    // .logoutSuccessUrl("/login?logout") // 로그아웃 성공 URL (기본 값 : "/login?logout")
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // 주소창에 요청해도 포스트로 인식하여 로그아웃
                    .deleteCookies("JSESSIONID") // 로그아웃 시 JSESSIONID 제거
                    .invalidateHttpSession(true) // 로그아웃 시 세션 종료
                    .clearAuthentication(true); // 로그아웃 시 권한 제거

            return http.build();
            /* @formatter:on */
        }

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
        }

        //AuthenticationmanagerBuilder에  패스워드 암호화를 위해
        // Spring Security에서 제공하는 BCryptPasswordEncoder를 추가 후 UserDetailsService를 추가하여
        // 로그인 시 UserDetailsService를 구현한 CustomUserDetailsService에서 사용자 확인 및 권한을 넣어줄 수 있도록 한다.
}
