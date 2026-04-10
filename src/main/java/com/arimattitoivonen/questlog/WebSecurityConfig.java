package com.arimattitoivonen.questlog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {


    @Bean
    public BCryptPasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/index", "/css/**", "/h2-console/**", "/gamelist").permitAll()
                        .requestMatchers("/addgame", "/savegame", "/editgame/**", "/deletegame/**").hasRole("ADMIN")
                        .requestMatchers("/addgenre", "/savegenre", "/editgenre/**", "/deletegenre/**", "/genrelist").hasRole("ADMIN")
                        .requestMatchers("/userlist", "/adduser", "/saveuser", "/edituser/**", "/deleteuser/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .headers(headers -> headers.frameOptions(frame -> frame.disable()))
                .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"))
                .formLogin(formlogin -> formlogin
                        .loginPage("/login")
                        .defaultSuccessUrl("/campaignlist", true)
                        .permitAll())
                .logout(logout -> logout
                        .permitAll());
        return http.build();
    }
}
