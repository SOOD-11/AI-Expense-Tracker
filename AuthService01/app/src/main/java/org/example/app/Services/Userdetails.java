package org.example.app.Services;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.example.app.entities.User;
import org.example.app.entities.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@SuperBuilder

@RequiredArgsConstructor


@Service

public class Userdetails extends User implements UserDetails {


    private String username;
    private String password;

     Collection<?extends GrantedAuthority> authorities;

    public Userdetails(User byUsername){
        this.username=byUsername.getUsername();
        this.password=byUsername.getPassword();
        List<GrantedAuthority> auth=new ArrayList<>();

        for(UserRole role: byUsername.getRoles()){

            auth.add(new SimpleGrantedAuthority(role.getName().toUpperCase()));
        }





    }
@Override
    public  Collection<?extends GrantedAuthority> getAuthorities(){

        return authorities;
    }

    public String setUsername(){

        return username;
    }


    public String setPassword(){

        return password;
    }
}
