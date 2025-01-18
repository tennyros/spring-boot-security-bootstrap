package com.github.tennyros.bootstrap.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.github.tennyros.bootstrap.services.AppUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String LOGIN_URL = "/login";
    private final SuccessUserHandler successUserHandler;
    private final AppUserDetailsService appUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(LOGIN_URL, "/error").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasAnyRole("ADMIN", "USER")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage(LOGIN_URL)
                .loginProcessingUrl("/process_login")
                .successHandler(successUserHandler)
                .failureUrl("/login?error=true")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl(LOGIN_URL);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(appUserDetailsService).passwordEncoder(getPasswordEncoder());
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}