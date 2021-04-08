/*
 * package com.demo.signup.config;
 * 
 * import java.util.Collections;
 * 
 * import javax.security.sasl.AuthenticationException;
 * 
 * import org.apache.tomcat.util.net.openssl.ciphers.Authentication; import
 * org.springframework.stereotype.Component;
 * 
 * @Component public class CustomAuthenticationProvider implements
 * AuthenticationProvider {
 * 
 * @Override public Authentication authenticate(Authentication auth) throws
 * AuthenticationException { String username = authentication.getName(); String
 * pwd = authentication.getCredentials().toString(); if
 * ("javadevjournal".equals(username) && "pass".equals(pwd)) { return new
 * UsernamePasswordAuthenticationToken(username, password,
 * Collections.emptyList()); } else { throw new
 * BadCredentialsException("User authentication failed!!!!"); } }
 * 
 * @Override public boolean supports(Class<?>auth) { return
 * auth.equals(UsernamePasswordAuthenticationToken.class); } }
 */