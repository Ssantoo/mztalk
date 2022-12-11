package com.mztalk.login.oauth;

import com.mztalk.login.auth.PrincipalDetails;
import com.mztalk.login.domain.entity.User;
import com.mztalk.login.properties.JwtProperties;
import com.mztalk.login.properties.LoginStatusProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.net.*;
import java.util.concurrent.ConcurrentHashMap;

import static com.mztalk.login.service.CookieFactory.getCookieFactoryInstance;
import static com.mztalk.login.service.JwtTokenFactory.getJwtTokenFactoryInstance;


@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();

        if(principalDetails.getUser().getNickname().equals("null")){
            Cookie usernameCookie = new Cookie("username", URLEncoder.encode(principalDetails.getUsername(),"UTF-8"));
            usernameCookie.setPath("/");
            response.addCookie(usernameCookie);
            response.sendRedirect("http://localhost:5501/editNickname");
        }

        ConcurrentHashMap<String, Cookie> cookieMap = getCookieFactoryInstance().getCookie(principalDetails.getUser());

        response.addCookie(cookieMap.get("jwtToken"));
        response.addCookie(cookieMap.get("refreshToken"));

        response.sendRedirect("http://localhost:5501/loginpage.html");
    }
}
