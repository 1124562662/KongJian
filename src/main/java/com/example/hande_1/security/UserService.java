package com.example.hande_1.security;

import com.example.hande_1.Data.UserJDBC;
import com.example.hande_1.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    UserRepository userRepository;

    @Autowired
    UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        UserJDBC userJDBC =this.userRepository.LoadUserByUsername(s);

        if(userJDBC == null){
            throw new UsernameNotFoundException("username is:"+s+", Not Found");
        }

        UserDetails userDetails= new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return Arrays.asList(new SimpleGrantedAuthority(userJDBC.getRole()));
            }

            @Override
            public String getPassword() {
                return userJDBC.getPassWord();
            }

            @Override
            public String getUsername() {
                return userJDBC.getUsername();
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {return true;}
        };

        return userDetails;

    }
}
