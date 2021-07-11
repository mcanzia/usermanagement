package com.service.usermanagement.security;

import com.service.usermanagement.models.Role;
import com.service.usermanagement.repositories.UserRepository;
import com.service.usermanagement.security.filters.JwtTokenFilter;
import com.service.usermanagement.services.SecurityDetailsService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletResponse;

/**
 * Configures Spring security for Spring application and enables features like token creation and filtering
 * @author Michael Canziani
 */
@EnableGlobalMethodSecurity(
        jsr250Enabled = true, // Enables @RolesAllowed annotation.
        prePostEnabled = true // Enables @PreAuthorize, @PostAuthorize, @PreFilter, @PostFilter annotations.
)
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    /** Used for retrieving security details about a user like email/password */
    private SecurityDetailsService securityDetailsService;
    /** Used for security token creation and validation */
    private JwtTokenFilter jwtTokenFilter;

    /**
     * Constructor for WebSecurityConfiguration, declares dependencies for Spring to inject
     * @param securityDetailsService
     * @param jwtTokenFilter
     */
    public WebSecurityConfiguration(SecurityDetailsService securityDetailsService, JwtTokenFilter jwtTokenFilter) {
        this.securityDetailsService = securityDetailsService;
        this.jwtTokenFilter = jwtTokenFilter;
    }

    /**
     * Creates Spring bean for authentication manager class
     * @return bean instance of authentication manager
     * @throws Exception if error occurs during creation
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * Builds authentication manager using security user details
     * @param auth authentication manager
     * @throws Exception if error occurs during configuration
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(securityDetailsService);
    }

    /**
     * Configures multiple aspects of the application security:
     * Disables CSRF, Sets session management to stateless, Sets exception handler for unauthorized requests
     * Allows you to specify which HTTP requests you want to permit
     * Sets JWT token filter ahead of UsernamePasswordAuthenticationFilter in filter chain
     * @param http
     * @throws Exception if error occurs during configuration
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        // Disable CSRF
        // Set session management to stateless
        // Set unauthorized requests exception handler
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .exceptionHandling();

        // Authorize requests
        http.authorizeRequests().antMatchers("/api/auth/**").permitAll()
                .antMatchers("/users/**").permitAll()
                .antMatchers("/groups/**").permitAll().anyRequest().authenticated();

        // Add JWT token filter
        http.addFilterBefore(
                jwtTokenFilter,
                UsernamePasswordAuthenticationFilter.class
        );
    }

    /**
     * Used to encode passwords with encryption algorithm
     * @return PasswordEncoder instance
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Used by Spring security if CORS is enabled, allows requests through CORS if matching pattern below
     * @return configurer instance
     */
    @Bean
    public WebMvcConfigurer corsFilter() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "PUT", "DELETE");
            }
        };
    }

}
