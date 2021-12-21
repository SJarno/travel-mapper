package com.sjarno.travelmapper.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Profile("dev")
@Configuration
public class DevSecurityConfiguration extends WebSecurityConfigurerAdapter {

    public void configure(WebSecurity security) throws Exception{
        security.ignoring().antMatchers("/**");
    }
    
}
