package com.danielsolawa.storeclient.config;


import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableZuulProxy
@EnableOAuth2Sso
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .logout()
                .and()
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers(
                        "/", "/login**", "/categories/**", "/logout", "/users/**",
                        "/product-search/**", "/home.html", "/category-view.html", "/product-view.html",
                        "/user-add.html", "/user-registered.html", "/activate-account.html", "/product-search.html",
                        "/error401.html").permitAll()
                .antMatchers("/admin-panel.html").hasAuthority("ADMIN")
                .anyRequest().authenticated();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/webjars/**");
    }
}
