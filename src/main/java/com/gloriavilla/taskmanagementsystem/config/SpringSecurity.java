package com.gloriavilla.taskmanagementsystem.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration

// @EnableWebSecurity applies security.  Right now it is disabled for testing is a marker annotation. It allows Spring to find and automatically apply the class to the global WebSecurity.
//@EnableWebSecurity
public class SpringSecurity {

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{

        http.csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((authz) -> authz


                                .requestMatchers("/register/**").permitAll()
                                .requestMatchers("/index").permitAll()
                                .requestMatchers("/task/save").permitAll()
                                .requestMatchers("/task/list").permitAll()
                                .requestMatchers("/task/edit").permitAll()
                                .requestMatchers("/task/route").permitAll()
                                .requestMatchers("/taskEdit?success").permitAll()

                                .requestMatchers("/users").hasRole("ADMIN")

                        //.requestMatchers(“/users”).permitAll()
                        .requestMatchers("/**")
                        //.anyRequest().permitAll() // allow everything through
                )
                .formLogin(
                        form -> form
                                .loginPage("/login")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/users")
                                .permitAll()
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()) ;
        return http.build();
    }
}
