package com.mztalk.login.config;

import com.mztalk.login.oauth.LoginSuccessHandler;
import com.mztalk.login.oauth.OAuth2LoginSuccessHandler;
import com.mztalk.login.oauth.PrincipalSocialOAuth2UserService;
import com.mztalk.login.auth.LoginAuthenticationFilter;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.web.filter.CorsFilter;

import java.net.http.HttpHeaders;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig  extends WebSecurityConfigurerAdapter {


    private String successUrl = "http://127.0.0.1:5501/main.html";
    @Autowired
   private PrincipalSocialOAuth2UserService principalSocialOAuth2UserService;


    @Autowired
    private LoginSuccessHandler loginSuccessHandler;


//    private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;

//    private final CorsFilter corsFilter;
    @Bean
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public SimpleUrlAuthenticationSuccessHandler getoAuth2LoginSuccessHandler(){
//        return new OAuth2LoginSuccessHandler();
//    }
//    @Bean
//    SimpleUrlAuthenticationSuccessHandler successHandler(){
//
//        return new SimpleUrlAuthenticationSuccessHandler(successUrl);
//
//   }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
//               .addFilter(corsFilter)
                .formLogin().disable()
                .httpBasic().disable()
                .addFilter(new LoginAuthenticationFilter(authenticationManager()))
                .authorizeRequests()
                .anyRequest().permitAll()
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(principalSocialOAuth2UserService)
                .and()
                .successHandler(loginSuccessHandler);



    }


}
