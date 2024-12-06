package com.example.nationsleague.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Slf4j
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfiguration {

    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults("");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();

    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }


    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user1 = User.builder()
                .username("user")
                .password("teszt")
                .authorities("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password("teszt")
                .authorities("ADMIN", "USER")
                .build();

        return new InMemoryUserDetailsManager(user1,admin);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .headers(header -> header.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req.requestMatchers( getUnsecuredPaths() ).permitAll())
                .authorizeHttpRequests(req -> req.requestMatchers("api/auth/**").permitAll())
                .authorizeHttpRequests(req -> req.requestMatchers("api/teams/**").hasAnyAuthority("USER", "ADMIN"))
                .authorizeHttpRequests(req -> req.requestMatchers("api/groups/**").hasAuthority("ADMIN"))
                .authorizeHttpRequests(req -> req.anyRequest().authenticated())
                .securityContext(securityContext ->
                        securityContext
                                .requireExplicitSave(true)
                                .securityContextRepository(new HttpSessionSecurityContextRepository())
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    private RequestMatcher[] getUnsecuredPaths() {
        return new RequestMatcher[] {
                new AntPathRequestMatcher("/**/*.*"),
                new AntPathRequestMatcher("/")
        };
    }



}
