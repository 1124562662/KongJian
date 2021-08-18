package com.example.hande_1.security;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private   UserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        //  auth.jdbcAuthentication().dataSource(this.dataSource).withUser("boss").password("{noop}zzq").authorities("ROLE_USER");

        auth.userDetailsService(this.userService).passwordEncoder(encode());

//     auth.inMemoryAuthentication().withUser("boss").password("{noop}zzq").authorities("ROLE_USER")
//                .and()
//                .withUser("b").password("b").authorities("ROLE_USER");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        // precedence : head -> tail
        // front will overwrite the end
        httpSecurity.authorizeRequests()
                .antMatchers("/MyAccount").access("hasRole('ROLE_USER')")
                     .antMatchers("/","/**").permitAll()
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/MyAccount")
                .and()
                .logout().logoutSuccessUrl("/");
    }

    @Bean
    public PasswordEncoder encode(){
        return new BCryptPasswordEncoder();
    }
}