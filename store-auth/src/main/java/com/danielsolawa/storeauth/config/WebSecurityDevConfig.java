package com.danielsolawa.storeauth.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@Configuration
@EnableResourceServer
@Profile("dev")
public class WebSecurityDevConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth
               .inMemoryAuthentication()
               .withUser("user")
               .password("user")
               .roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .requestMatchers()
                .antMatchers("/login", "/oauth/authorize")
                .and()
                .authorizeRequests()
                .antMatchers("/h2-console").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .permitAll();


        http.csrf().disable();
        http.headers().frameOptions().disable();


    }
}
