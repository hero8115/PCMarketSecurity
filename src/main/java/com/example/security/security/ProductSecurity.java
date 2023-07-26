package com.example.security.security;

import com.example.security.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ProductSecurity extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("owner").password(passwordEncoder().encode("123")).roles("OWNER")
                .and()
                .withUser("admin").password(passwordEncoder().encode("123")).roles("ADMIN")
                .and()
                .withUser("user").password(passwordEncoder().encode("123")).roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
//                .antMatchers(HttpMethod.GET, "/product").hasAnyRole("USER","ADMIN","OWNER")
//                .antMatchers(HttpMethod.GET,"/product/*").hasAnyRole("USER","ADMIN","OWNER")
//                .antMatchers(HttpMethod.POST,"/product/**").hasAnyRole("ADMIN","OWNER")
//                .antMatchers("/product/**").hasAnyRole("OWNER")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
