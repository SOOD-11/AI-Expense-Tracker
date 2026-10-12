package org.example.app.Authentication;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.app.Services.Jwt;
import org.example.app.Services.Userdetailsimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Configuration

public class JwtAuthFilter extends OncePerRequestFilter {

@Autowired
    private final Jwt jwtService;

@Autowired
    private final Userdetailsimpl userDetailsService;

    public JwtAuthFilter(Jwt jwtService,Userdetailsimpl userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService=userDetailsService;
    }

    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterchain) throws ServletException, IOException {

        {


            String path = req.getRequestURI();
            if (path.startsWith("/auth/v1/login") ||
                    path.startsWith("/auth/v1/SignUp") ||
                    path.startsWith("/auth/v1/refreshtoken")) {
                filterchain.doFilter(req, res);
                return;
            }
            String authHeader=req.getHeader("Authorization");
            String token =null;
            String username=null;

            if(authHeader !=null && authHeader.startsWith("Bearer ")){


                token=authHeader.substring(7);
                username=jwtService.extractUsername(token );
            }



            if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){

                UserDetails  userdetails= userDetailsService.loadUserByUsername(username);
                if(jwtService.validateToken(token,userdetails)){

                    UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userdetails,null,userdetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);






                }







            }
        }


filterchain.doFilter(req,res);

    }



}
