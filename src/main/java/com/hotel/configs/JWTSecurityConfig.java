package com.hotel.configs;

import com.hotel.model.constants.Role;
import com.hotel.security.JWTAuthenticationEntryPoint;
import com.hotel.security.JWTAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author mostafa
 * @email mostafa.ghany.omar@gmail.com
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class JWTSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private UserDetailsService userDetailsService;

    @Value("${api.path}")
    private String apiPath;

    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(this.userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        return new JWTAuthenticationFilter();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                //Disable cross site request forgery
                .csrf().disable()
                //When security exception occurs, 401 is thrown in the entry point.
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                //The Application is completely stateless so no session is needed
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                //Which paths and how they should be authorized starts from here
                .and().authorizeRequests()
                //Allow anonymous resource requests
                .antMatchers(
                        HttpMethod.GET,
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js"
                ).permitAll()
                //Swagger UI White List
                .antMatchers(
                        "/swagger-resources/**",
                        "/swagger-ui.html",
                        "/v2/api-docs",
                        "/webjars/**"
                ).permitAll()
                //Authentication Services
                .antMatchers(
                        "/auth/**"
                ).permitAll()
                .antMatchers(apiPath + "/hotel/**").hasAuthority(Role.STAFF.name())
                .antMatchers(apiPath + "/book/**").hasAuthority(Role.GUEST.name())
                .anyRequest().authenticated();

        //Custom JWT based security filter in order to do our own validation procedures.
        httpSecurity
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        //Disable page caching
        httpSecurity.headers().cacheControl();
    }
}
