package org.example.app.Authentication;

import lombok.Data;
import org.example.app.Repository.UserDetailsRepo;
import org.example.app.Services.Userdetailsimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@Data
// ITS USED AS THE SECURITY LAYER   BASICALLY TELLING WHICH ROUTES NEED NOT TO BE AUTHENTICATED
public class SecurityConfig {
@Autowired
    private final PasswordEncoder PasswordEncrypt;

@Autowired
private  final Userdetailsimpl userdetailsservice;





    @Bean
    @Autowired
    public UserDetailsService userService(UserDetailsRepo userrepo, PasswordEncoder PasswordEncrypt){

        return new Userdetailsimpl(PasswordEncrypt,userrepo);






    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   JwtAuthFilter jwtAuthFilter,
                                                   AuthenticationProvider authenticationProvider) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers( "/auth/v1/login",
                                "/auth/v1/refreshtoken",
                                "/auth/v1/SignUp",
                                "/error",
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic(Customizer.withDefaults());   // ✅ Correct placement

        return http.build();
    }

@Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userdetailsservice);
        authenticationProvider.setPasswordEncoder(PasswordEncrypt);

return authenticationProvider;

    }

@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
return config.getAuthenticationManager();



    }



}
