package com.example.application.auth;

import com.vaadin.flow.spring.security.VaadinWebSecurity;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class SecurityConfiguration extends VaadinWebSecurity {

    @Autowired
    private DynamicRouteService dynamicRouteService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf(registry -> {
            registry.ignoringRequestMatchers(new AntPathRequestMatcher("/connect/**"));
            registry.ignoringRequestMatchers(new AntPathRequestMatcher("/VAADIN/**"));
            registry.ignoringRequestMatchers(new AntPathRequestMatcher("/login"));
            registry.ignoringRequestMatchers(new AntPathRequestMatcher("/logout"));
        });
        http.authorizeHttpRequests(registry -> {
            registry.requestMatchers(new PublicClientViewRequestMatcher()).permitAll();
        });
        super.configure(http);
        setLoginView(http, "/login");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        // This way interfere with login action
        // web.ignoring().requestMatchers(new PublicClientViewRequestMatcher());
    }

    @Bean
    public UserDetailsManager userDetailsService() {
        // Configure users and roles in memory
        return new InMemoryUserDetailsManager(
                // the {noop} prefix tells Spring that the password is not encoded
                User.withUsername("user").password("{noop}user").roles("USER").build(),
                User.withUsername("admin").password("{noop}admin").roles("ADMIN", "USER").build()
        );
    }

    private class PublicClientViewRequestMatcher implements org.springframework.security.web.util.matcher.RequestMatcher {
        @Override
        public boolean matches(HttpServletRequest request) {
            return dynamicRouteService.shouldBePublic(request.getRequestURI());
        }
    }

}
