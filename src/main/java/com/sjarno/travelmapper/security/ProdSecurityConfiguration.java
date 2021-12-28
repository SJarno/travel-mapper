package com.sjarno.travelmapper.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Profile("prod")
@EnableWebSecurity
public class ProdSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserAccountDetailsService userDetailsService;

    protected void configure(HttpSecurity http) throws Exception {

        String[] whitelistGet = new String[] {
            "/css/**", "/images/**", "/js/**" };
        String[] whitelistPost = new String[] {
            "/register", "/login"
        };

        http.headers().frameOptions().sameOrigin();

    
        http.authorizeRequests()
            .antMatchers(HttpMethod.GET, "/register-page", "/info-page").permitAll()
            .antMatchers(HttpMethod.POST, whitelistPost).permitAll()
            .antMatchers(whitelistGet).permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/index")
            .loginProcessingUrl("/login")
            .permitAll();
            
        http.logout()
            .logoutSuccessUrl("/")
            .clearAuthentication(true)
            .permitAll()
            .and()
            .csrf().disable();
        
        
            
    }
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
