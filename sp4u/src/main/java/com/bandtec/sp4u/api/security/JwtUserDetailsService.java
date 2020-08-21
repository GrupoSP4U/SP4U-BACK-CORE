package com.bandtec.sp4u.api.security;

import java.util.ArrayList;

import com.bandtec.sp4u.domain.interfaces.dao.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {


    public JwtUserDetailsService() {}

    @Override
    public UserDetails loadUserByUsername(String email){
        if (JwtUserData.UserData.getEmail().equals(email)) {
            return new User(email, "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("Usuário não encontrado com o email: " + email);
        }
    }
}
